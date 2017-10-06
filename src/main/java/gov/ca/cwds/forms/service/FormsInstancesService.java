package gov.ca.cwds.forms.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonschema.core.exceptions.ProcessingException;
import com.github.fge.jsonschema.core.report.ProcessingReport;
import com.github.fge.jsonschema.main.JsonSchema;
import com.google.inject.Inject;
import gov.ca.cwds.forms.exceptions.FormValidationException;
import gov.ca.cwds.forms.persistence.dao.FormsDao;
import gov.ca.cwds.forms.persistence.model.FormInstance;
import gov.ca.cwds.forms.service.dto.FormInstanceDTO;
import gov.ca.cwds.forms.service.dto.FormInstanceStatus;
import gov.ca.cwds.forms.service.mapper.FormInstanceMapper;
import gov.ca.cwds.forms.web.rest.parameters.FormParameterObject;

/**
 * @author CWDS TPT-2 Team
 */
public class FormsInstancesService extends
    TypedCrudServiceAdapter<FormParameterObject, FormInstanceDTO, FormInstanceDTO> {

  private FormsDao dao;

  @Inject
  private FormsSchemasService schemasService;

  @Inject
  private FormInstanceMapper formInstanceMapper;

  @Inject
  public FormsInstancesService(FormsDao dao) {
    this.dao = dao;
  }

  @Override
  public FormInstanceDTO create(FormInstanceDTO dto) {
    validateContent(dto);
    FormInstance entity = formInstanceMapper.toForm(dto);
    entity.setStatus(FormInstanceStatus.DRAFT);
    return formInstanceMapper.toFormDTO(dao.create(entity));
  }

  @Override
  public FormInstanceDTO find(FormParameterObject params) {
    return formInstanceMapper.toFormDTO(dao.find(params.getId()));
  }

  @Override
  public FormInstanceDTO update(FormParameterObject params, FormInstanceDTO dto) {
    validateContent(dto);
    FormInstance entity = formInstanceMapper.toForm(dto);
    entity.setId(params.getId());
    entity.setStatus(FormInstanceStatus.IN_PROGRESS);
    return formInstanceMapper.toFormDTO(dao.update(entity));
  }

  @Override
  public FormInstanceDTO delete(FormParameterObject params) {
    return formInstanceMapper.toFormDTO(dao.delete(params.getId()));
  }

  private void validateContent(FormInstanceDTO dto) {
    ObjectMapper objectMapper = new ObjectMapper();
    JsonSchema schema;
    try {
      schema = schemasService.getFormSchema(dto.getName(), dto.getSchemaVersion());
    } catch (Exception e) {
      throw new IllegalArgumentException(
          "Can't get Form Schema for name: " + dto.getName() + " and Schema Version: " + dto
              .getSchemaVersion(), e);
    }
    JsonNode contentJson = objectMapper.valueToTree(dto.getContent());
    try {
      ProcessingReport report = schema.validate(contentJson);
      if (!report.isSuccess()) {
        throw new FormValidationException(report);
      }
    } catch (ProcessingException e) {
      throw new IllegalArgumentException(e);
    }
  }


}
