package gov.ca.cwds.forms.service.mapper;

import gov.ca.cwds.forms.persistence.model.FormInstance;
import gov.ca.cwds.forms.service.dto.FormInstanceDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * @author CWDS TPT-2 Team
 */
@SuppressWarnings("squid:S1214")
@Mapper
public interface FormInstanceMapper {

  FormInstanceMapper INSTANCE = Mappers.getMapper(FormInstanceMapper.class);

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "status", ignore = true)
  @Mapping(target = "name", source = "name")
  @Mapping(target = "schemaVersion", source = "schemaVersion")
  @Mapping(target = "parentFormId", source = "parentFormId")
  @Mapping(target = "parentFormName", source = "parentFormName")
  @Mapping(target = "content", source = "content")
  @Mapping(target = "formsPackage", ignore = true)
  FormInstance toForm(FormInstanceDTO dto);


  @Mapping(target = "formId", source = "id")
  @Mapping(target = "name", source = "name")
  @Mapping(target = "schemaVersion", source = "schemaVersion")
  @Mapping(target = "parentFormId", source = "parentFormId")
  @Mapping(target = "parentFormName", source = "parentFormName")
  @Mapping(target = "packageId", source = "formsPackage.id")
  @Mapping(target = "content", source = "content")
  @Mapping(target = "messages", ignore = true)
  FormInstanceDTO toFormDTO(FormInstance formInstance);
}
