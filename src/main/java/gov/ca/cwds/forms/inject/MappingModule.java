package gov.ca.cwds.forms.inject;

import com.google.inject.AbstractModule;
import gov.ca.cwds.forms.service.mapper.FormInstanceMapper;
import gov.ca.cwds.forms.service.mapper.FormSchemaMapper;
import gov.ca.cwds.forms.service.mapper.FormsPackageMapper;

/**
 * DI (dependency injection) setup for mapping classes.
 *
 * @author CWDS TPT-2 Team
 */

public class MappingModule extends AbstractModule {

  @Override
  protected void configure() {
    bind(FormSchemaMapper.class).to(FormSchemaMapper.INSTANCE.getClass()).asEagerSingleton();
    bind(FormInstanceMapper.class).to(FormInstanceMapper.INSTANCE.getClass()).asEagerSingleton();
    bind(FormsPackageMapper.class).to(FormsPackageMapper.INSTANCE.getClass()).asEagerSingleton();
  }

}


