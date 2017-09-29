package gov.ca.cwds.forms.inject;

import com.google.inject.AbstractModule;
import gov.ca.cwds.forms.service.mapper.FormInstanceMapper;
import gov.ca.cwds.forms.service.mapper.FormSchemaMapper;

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
  }

}


