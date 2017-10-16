package gov.ca.cwds.forms.service.mapper;

import gov.ca.cwds.forms.persistence.model.FormSchema;
import gov.ca.cwds.forms.service.dto.FormSchemaDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * @author CWDS TPT-2 Team
 */
@SuppressWarnings("squid:S1214")
@Mapper
public interface FormSchemaMapper {

  FormSchemaMapper INSTANCE = Mappers.getMapper(FormSchemaMapper.class);

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "formName", source = "formName")
  @Mapping(target = "schemaVersion", source = "schemaVersion")
  @Mapping(target = "description", source = "description")
  @Mapping(target = "jsonSchema", source = "jsonSchema")
  @Mapping(target = "uiSchema", source = "uiSchema")
  FormSchema toFormSchema(FormSchemaDTO dto);


  @Mapping(target = "formSchemaId", source = "id")
  @Mapping(target = "formName", source = "formName")
  @Mapping(target = "schemaVersion", source = "schemaVersion")
  @Mapping(target = "description", source = "description")
  @Mapping(target = "jsonSchema", source = "jsonSchema")
  @Mapping(target = "uiSchema", source = "uiSchema")
  @Mapping(target = "messages", ignore = true)
  FormSchemaDTO toFormSchemaDTO(FormSchema formSchema);
}
