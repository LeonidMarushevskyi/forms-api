package gov.ca.cwds.forms.inject;

import static gov.ca.cwds.forms.Constants.UnitOfWork.FORMS_UNIT_OF_WORK;

import com.google.common.collect.ImmutableList;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import gov.ca.cwds.forms.FormsApiConfiguration;
import gov.ca.cwds.forms.persistence.model.FormInstance;
import gov.ca.cwds.forms.persistence.model.FormSchema;
import io.dropwizard.db.PooledDataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.hibernate.SessionFactoryFactory;
import io.dropwizard.setup.Bootstrap;
import org.hibernate.SessionFactory;

/**
 * @author CWDS TPT-2 Team
 */
public class DataAccessModule extends AbstractModule {


  private final ImmutableList<Class<?>> formsEntities = ImmutableList.<Class<?>>builder().add(
      FormSchema.class,
      FormInstance.class
  ).build();

  private final HibernateBundle<FormsApiConfiguration> formsHibernateBundle =
      new HibernateBundle<FormsApiConfiguration>(formsEntities, new SessionFactoryFactory()) {
        @Override
        public PooledDataSourceFactory getDataSourceFactory(FormsApiConfiguration configuration) {
          return configuration.getFormsDataSourceFactory();
        }

        @Override
        public String name() {
          return FORMS_UNIT_OF_WORK;
        }

        @Override
        public void configure(org.hibernate.cfg.Configuration configuration) {
          configuration.addPackage("gov.ca.cwds.forms.persistence.model");
        }
      };


  public DataAccessModule(Bootstrap<? extends FormsApiConfiguration> bootstrap) {
    bootstrap.addBundle(formsHibernateBundle);
  }

  @Override
  protected void configure() {
    //do nothing
  }

  @Provides
  @FormsSessionFactory
  SessionFactory calsnsSessionFactory() {
    return formsHibernateBundle.getSessionFactory();
  }


  @Provides
  @FormsHibernateBundle
  public HibernateBundle<FormsApiConfiguration> getFormsHibernateBundle() {
    return formsHibernateBundle;
  }

}
