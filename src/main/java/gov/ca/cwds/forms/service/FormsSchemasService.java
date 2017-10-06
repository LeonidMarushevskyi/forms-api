package gov.ca.cwds.forms.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonschema.main.JsonSchema;
import com.github.fge.jsonschema.main.JsonSchemaFactory;
import com.google.inject.Inject;
import gov.ca.cwds.forms.persistence.dao.FormsSchemasDao;
import gov.ca.cwds.forms.persistence.model.FormSchema;
import gov.ca.cwds.forms.service.dto.FormSchemaDTO;
import gov.ca.cwds.forms.service.mapper.FormSchemaMapper;
import gov.ca.cwds.forms.web.rest.parameters.FormSchemaParameterObject;

/**
 * @author CWDS TPT-2 Team
 */
public class FormsSchemasService extends
    TypedCrudServiceAdapter<FormSchemaParameterObject, FormSchemaDTO, FormSchemaDTO> {

  final JsonSchemaFactory factory = JsonSchemaFactory.byDefault();
  private FormsSchemasDao dao;
  @Inject
  private FormSchemaMapper formSchemaMapper;

  @Inject
  public FormsSchemasService(FormsSchemasDao dao) {
    this.dao = dao;
  }

  @Override
  public FormSchemaDTO create(FormSchemaDTO dto) {
    FormSchema entity = formSchemaMapper.toFormSchema(dto);
    return formSchemaMapper.toFormSchemaDTO(dao.create(entity));
  }

  @Override
  public FormSchemaDTO find(FormSchemaParameterObject params) {
    if (params.getId() != null) {
      return formSchemaMapper.toFormSchemaDTO(dao.find(params.getId()));
    } else if (params.getName() != null && params.getVersion() != null) {
      return formSchemaMapper
          .toFormSchemaDTO(dao.findByNameAndVersion(params.getName(), params.getVersion()));
    } else {
      throw new IllegalArgumentException("Expected schema Id or Name and Version");
    }
  }

  @Override
  public FormSchemaDTO update(FormSchemaParameterObject params, FormSchemaDTO dto) {
    FormSchema entity = formSchemaMapper.toFormSchema(dto);
    entity.setId(params.getId());
    return formSchemaMapper.toFormSchemaDTO(dao.update(entity));
  }

  @Override
  public FormSchemaDTO delete(FormSchemaParameterObject params) {
    return formSchemaMapper.toFormSchemaDTO(dao.delete(params.getId()));
  }

  public JsonSchema getFormSchema(String name, String schemaVersion) {
    FormSchema formSchema = dao.findByNameAndVersion(name, schemaVersion);
    if (formSchema == null) {
      return null;
    }
    ObjectMapper objectMapper = new ObjectMapper();
    try {
      return factory.getJsonSchema(objectMapper.valueToTree(formSchema.getJsonSchema()));
    } catch (Exception e) {
      throw new IllegalArgumentException(e);
    }
  }
}
