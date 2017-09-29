package gov.ca.cwds.forms.service;

import com.google.inject.Inject;
import gov.ca.cwds.forms.persistence.dao.FormsDao;
import gov.ca.cwds.forms.persistence.model.FormInstance;
import gov.ca.cwds.forms.service.dto.FormCollectionDTO;
import gov.ca.cwds.forms.service.dto.FormInstanceDTO;
import gov.ca.cwds.forms.service.mapper.FormInstanceMapper;
import gov.ca.cwds.rest.api.Response;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author CWDS CALS API Team
 */
public class FormsInstancesCollectionService extends CrudServiceAdapter {

  private FormsDao dao;

  @Inject
  private FormInstanceMapper formInstanceMapper;

  @Inject
  public FormsInstancesCollectionService(FormsDao dao) {
    this.dao = dao;
  }

  @Override
  public Response find(Serializable params) {
    List<FormInstance> allSchemasEntities = dao.findAll();
    List<FormInstanceDTO> collection = allSchemasEntities.stream()
        .map(formInstanceMapper::toFormDTO)
        .collect(Collectors.toList());
    return new FormCollectionDTO(collection);
  }
}
