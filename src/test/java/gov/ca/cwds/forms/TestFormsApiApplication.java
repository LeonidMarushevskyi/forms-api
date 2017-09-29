package gov.ca.cwds.forms;

import com.google.inject.Module;
import com.google.inject.Provides;
import gov.ca.cwds.forms.inject.ApplicationModule;
import gov.ca.cwds.forms.inject.DataAccessModule;
import io.dropwizard.hibernate.UnitOfWorkAwareProxyFactory;
import io.dropwizard.setup.Bootstrap;

/**
 * @author CWDS TPT-2 Team
 */

public class TestFormsApiApplication extends BaseFormsApiApplication<FormsApiConfiguration> {

  @Override
  public Module applicationModule(Bootstrap<FormsApiConfiguration> bootstrap) {
    return new ApplicationModule<FormsApiConfiguration>(bootstrap) {

      @Override
      protected void configure() {
        super.configure();
        install(new DataAccessModule(getBootstrap()) {

          @Provides
          UnitOfWorkAwareProxyFactory provideUnitOfWorkAwareProxyFactory() {
            return new UnitOfWorkAwareProxyFactory(
                getFormsHibernateBundle()
            );
          }
        });
      }

    };
  }

}
