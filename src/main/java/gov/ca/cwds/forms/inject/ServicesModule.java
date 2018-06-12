package gov.ca.cwds.forms.inject;

import com.google.inject.AbstractModule;
import gov.ca.cwds.forms.service.FormsInstancesCollectionService;
import gov.ca.cwds.forms.service.FormsInstancesService;
import gov.ca.cwds.forms.service.FormsPackageService;
import gov.ca.cwds.forms.service.FormsPackagesCollectionService;
import gov.ca.cwds.forms.service.FormsSchemasCollectionService;
import gov.ca.cwds.forms.service.FormsSchemasService;

/**
 * Identifies all FORMS_UNIT_OF_WORK API business layer (services) classes available for dependency
 * injection by Guice.
 *
 * @author TPT-2 Team
 */
public class ServicesModule extends AbstractModule {

  /**
   * Default constructor
   */
  public ServicesModule() {
    // Do nothing - Default constructor
  }

  @Override
  protected void configure() {
    bind(FormsSchemasService.class);
    bind(FormsSchemasCollectionService.class);
    bind(FormsInstancesService.class);
    bind(FormsInstancesCollectionService.class);
    bind(FormsPackageService.class);
    bind(FormsPackagesCollectionService.class);
  }

}
