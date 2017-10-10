package gov.ca.cwds.forms;

import com.codahale.metrics.health.HealthCheck;
import com.codahale.metrics.health.HealthCheckRegistry;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.google.inject.Injector;
import gov.ca.cwds.forms.exceptions.FormValidationExceptionMapperImpl;
import gov.ca.cwds.forms.inject.InjectorHolder;
import gov.ca.cwds.forms.web.rest.filters.RequestExecutionContextFilter;
import gov.ca.cwds.forms.web.rest.filters.RequestResponseLoggingFilter;
import gov.ca.cwds.logging.LoggingContext;
import gov.ca.cwds.rest.BaseApiApplication;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.setup.Environment;
import java.util.EnumSet;
import javax.servlet.DispatcherType;
import org.glassfish.jersey.linking.DeclarativeLinkingFeature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author CWDS TPT-2 Team
 */
public abstract class BaseFormsApiApplication<T extends FormsApiConfiguration> extends
    BaseApiApplication<T> {

  private static final Logger LOG = LoggerFactory.getLogger(BaseFormsApiApplication.class);
  private static final String LIQUIBASE_FORMS_DATABASE_CREATE_SCHEMA_XML = "liquibase/forms_schema.xml";
  private static final String LIQUIBASE_FORMS_DATABASE_MASTER_XML = "liquibase/forms_database_master.xml";
  private static final String HIBERNATE_DEFAULT_SCHEMA_PROPERTY_NAME = "hibernate.default_schema";

  @Override
  public void runInternal(T configuration, Environment environment) {

    registerCustomExceptionMappers(environment);

    environment.getObjectMapper()
        .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);

    if (configuration.isUpgradeDbOnStart()) {
      upgardeDB(configuration);
    }

    environment
        .jersey()
        .getResourceConfig()
        .packages(getClass().getPackage().getName())
        .register(DeclarativeLinkingFeature.class);

    runDataSourceHealthChecks(environment);

    Injector injector = guiceBundle.getInjector();

    // Providing access to the guice injector from external classes such as custom validators
    InjectorHolder.INSTANCE.setInjector(injector);

    environment.servlets()
        .addFilter("RequestExecutionContextManagingFilter",
            injector.getInstance(RequestExecutionContextFilter.class))
        .addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST), true, "/*");

    environment.servlets()
        .addFilter("AuditAndLoggingFilter",
            injector.getInstance(RequestResponseLoggingFilter.class))
        .addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST), true, "/*");
  }

  private void runDataSourceHealthChecks(Environment environment) {
    HealthCheckRegistry healthCheckRegistry = environment.healthChecks();
    doHealthCheck(healthCheckRegistry, Constants.UnitOfWork.FORMS_UNIT_OF_WORK);
  }

  private void doHealthCheck(HealthCheckRegistry healthCheckRegistry, String key) {
    HealthCheck.Result result = healthCheckRegistry.runHealthCheck(key);
    if (!result.isHealthy()) {
      LOG.error("Fail - {}: {}", key, result.getMessage());
    }
  }

  private void registerCustomExceptionMappers(Environment environment) {
    LoggingContext loggingContext = guiceBundle.getInjector().getInstance(LoggingContext.class);
    environment.jersey().register(new FormValidationExceptionMapperImpl(loggingContext));
  }

  private void upgardeDB(FormsApiConfiguration configuration) {
    LOG.info("Upgrading DB...");

    DataSourceFactory formsDataSourceFactory = configuration.getFormsDataSourceFactory();
    DatabaseHelper databaseHelper = new DatabaseHelper(formsDataSourceFactory.getUrl(),
        formsDataSourceFactory.getUser(),
        formsDataSourceFactory.getPassword());
    try {
      databaseHelper.runScript(LIQUIBASE_FORMS_DATABASE_CREATE_SCHEMA_XML);
      databaseHelper.runScript(LIQUIBASE_FORMS_DATABASE_MASTER_XML,
          formsDataSourceFactory.getProperties().get(HIBERNATE_DEFAULT_SCHEMA_PROPERTY_NAME));
    } catch (Exception e) {
      LOG.error("Upgarding of DB is failed. ", e);
      throw new IllegalStateException(e);
    }

    LOG.info("Finish Upgrading DB");
  }
}
