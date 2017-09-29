package gov.ca.cwds.forms.service;

import com.google.inject.Inject;
import gov.ca.cwds.forms.persistence.dao.FormsSchemasDao;
import gov.ca.cwds.forms.persistence.model.FormSchema;
import gov.ca.cwds.forms.service.dto.FormSchemaCollectionDTO;
import gov.ca.cwds.forms.service.dto.FormSchemaDTO;
import gov.ca.cwds.forms.service.mapper.FormSchemaMapper;
import gov.ca.cwds.rest.api.Response;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author CWDS CALS API Team
 */
public class FormsSchemasCollectionService extends CrudServiceAdapter {

  private FormsSchemasDao dao;

  @Inject
  private FormSchemaMapper formSchemaMapper;

  @Inject
  public FormsSchemasCollectionService(FormsSchemasDao dao) {
    this.dao = dao;
  }

  @Override
  public Response find(Serializable params) {
    List<FormSchema> allSchemasEntities = dao.findAll();
    List<FormSchemaDTO> collection = allSchemasEntities.stream()
        .map(formSchemaMapper::toFormSchemaDTO)
        .collect(Collectors.toList());
    return new FormSchemaCollectionDTO(collection);
  }
}
