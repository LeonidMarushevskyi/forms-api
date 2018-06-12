package gov.ca.cwds.forms.inject;

import com.google.inject.AbstractModule;
import com.google.inject.Injector;
import com.google.inject.Provides;
import gov.ca.cwds.forms.service.FormsInstancesCollectionService;
import gov.ca.cwds.forms.service.FormsInstancesService;
import gov.ca.cwds.forms.service.FormsPackageService;
import gov.ca.cwds.forms.service.FormsPackagesCollectionService;
import gov.ca.cwds.forms.service.FormsSchemasCollectionService;
import gov.ca.cwds.forms.service.FormsSchemasService;
import gov.ca.cwds.forms.service.dto.FormInstanceDTO;
import gov.ca.cwds.forms.service.dto.FormSchemaDTO;
import gov.ca.cwds.forms.service.dto.FormsPackageDTO;
import gov.ca.cwds.forms.web.rest.FormsInstancesResource;
import gov.ca.cwds.forms.web.rest.FormsPackagesResource;
import gov.ca.cwds.forms.web.rest.FormsSchemasResource;
import gov.ca.cwds.forms.web.rest.parameters.FormParameterObject;
import gov.ca.cwds.forms.web.rest.parameters.FormSchemaParameterObject;
import gov.ca.cwds.forms.web.rest.parameters.FormsPackageParameterObject;
import gov.ca.cwds.forms.web.rest.system.SystemInformationResource;
import gov.ca.cwds.rest.resources.ResourceDelegate;
import gov.ca.cwds.rest.resources.ServiceBackedResourceDelegate;
import gov.ca.cwds.rest.resources.TypedResourceDelegate;
import gov.ca.cwds.rest.resources.TypedServiceBackedResourceDelegate;

/**
 * Identifies all FORMS_UNIT_OF_WORK API domain resource classes available for dependency injection
 * by Guice.
 *
 * @author TPT-2 Team
 */
public class ResourcesModule extends AbstractModule {

  /**
   * Default constructor
   */
  public ResourcesModule() {
    // Do nothing - Default Constructor
  }

  @Override
  protected void configure() {
    bind(SystemInformationResource.class);

    bind(FormsSchemasResource.class);
    bind(FormsInstancesResource.class);
    bind(FormsPackagesResource.class);
  }

  @Provides
  @FormsSchemasServiceBackedResource
  public TypedResourceDelegate<FormSchemaParameterObject, FormSchemaDTO> formsSchemasServiceBackedResource(
      Injector injector) {
    return new TypedServiceBackedResourceDelegate<>(
        injector.getInstance(FormsSchemasService.class));
  }

  @Provides
  @FormsSchemasCollectionServiceBackedResource
  public ResourceDelegate formsSchemasCollectionServiceBackedResource(
      Injector injector) {
    return new ServiceBackedResourceDelegate(
        injector.getInstance(FormsSchemasCollectionService.class));
  }

  @Provides
  @FormsServiceBackedResource
  public TypedResourceDelegate<FormParameterObject, FormInstanceDTO> formsServiceBackedResource(
      Injector injector) {
    return new TypedServiceBackedResourceDelegate<>(
        injector.getInstance(FormsInstancesService.class));
  }

  @Provides
  @FormsCollectionServiceBackedResource
  public ResourceDelegate formsCollectionServiceBackedResource(
      Injector injector) {
    return new ServiceBackedResourceDelegate(
        injector.getInstance(FormsInstancesCollectionService.class));
  }

  @Provides
  @FormsPackageServiceBackedResource
  public TypedResourceDelegate<FormsPackageParameterObject, FormsPackageDTO> formsPackageServiceBackedResource(
      Injector injector) {
    return new TypedServiceBackedResourceDelegate<>(
        injector.getInstance(FormsPackageService.class));
  }

  @Provides
  @FormsPackageCollectionServiceBackedResource
  public ResourceDelegate formsPackageCollectionServiceBackedResource(
      Injector injector) {
    return new ServiceBackedResourceDelegate(
        injector.getInstance(FormsPackagesCollectionService.class));
  }

}
