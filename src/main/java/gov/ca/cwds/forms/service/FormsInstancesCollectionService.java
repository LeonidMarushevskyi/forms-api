package gov.ca.cwds.forms.service;

import com.google.inject.Inject;
import gov.ca.cwds.forms.persistence.dao.FormsDao;
import gov.ca.cwds.forms.persistence.model.FormInstance;
import gov.ca.cwds.forms.service.dto.FormCollectionDTO;
import gov.ca.cwds.forms.service.dto.FormInstanceDTO;
import gov.ca.cwds.forms.service.mapper.FormInstanceMapper;
import gov.ca.cwds.forms.web.rest.parameters.FormParameterObject;
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
    FormParameterObject paramObj = (FormParameterObject) params;
    Response res;
    if (paramObj.getName() != null) {
      res = getFormsByName(paramObj.getName());
    } else {
      throw new IllegalArgumentException("Form name expected but got NULL");
    }
    return res;
  }

  private Response getFormsByName(String name) {
    List<FormInstance> allSchemasEntities = dao.findByName(name);
    return convertToCollectionDTO(allSchemasEntities);
  }

  private Response convertToCollectionDTO(List<FormInstance> allSchemasEntities) {
    List<FormInstanceDTO> collection = allSchemasEntities.stream()
        .map(formInstanceMapper::toFormDTO)
        .collect(Collectors.toList());
    return new FormCollectionDTO(collection);
  }

}
