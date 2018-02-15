package gov.ca.cwds.forms.service;

import com.google.inject.Inject;
import gov.ca.cwds.forms.persistence.dao.FormsPackageDao;
import gov.ca.cwds.forms.persistence.model.FormsPackage;
import gov.ca.cwds.forms.service.dto.FormsPackageCollectionDTO;
import gov.ca.cwds.forms.service.dto.FormsPackageDTO;
import gov.ca.cwds.forms.service.mapper.FormsPackageMapper;
import gov.ca.cwds.forms.web.rest.parameters.FormsPackageParameterObject;
import gov.ca.cwds.rest.api.Response;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author CWDS CALS API Team
 */
public class FormsPackagesCollectionService extends CrudServiceAdapter {

  private FormsPackageDao dao;

  @Inject
  private FormsPackageMapper formsPackageMapper;

  @Inject
  public FormsPackagesCollectionService(FormsPackageDao dao) {
    this.dao = dao;
  }

  @Override
  public Response find(Serializable parameters) {
    if (!(parameters instanceof FormsPackageParameterObject)) {
      throw new IllegalArgumentException(
          "parameters must be instance of FormsPackageParameterObject");
    }
    FormsPackageParameterObject params = (FormsPackageParameterObject) parameters;

    List<FormsPackage> packages = null;
    if (params.getExernalId() != null && params.getStatus() != null) {
      packages = dao.findByExternalIdAndStatus(params.getExernalId(), params.getStatus());
    } else if (params.getExernalId() != null) {
      packages = dao.findByExternalId(params.getExernalId());
    } else {
      packages = dao.findAll();
    }

    if (packages == null || packages.isEmpty()) {
      return null;
    }

    List<FormsPackageDTO> collection = packages.stream().map(formsPackageMapper::toDTO)
        .collect(Collectors.toList());

    return new FormsPackageCollectionDTO(collection);
  }
}
