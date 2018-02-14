package gov.ca.cwds.forms.service;

import com.google.inject.Inject;
import gov.ca.cwds.forms.persistence.dao.FormsPackageDao;
import gov.ca.cwds.forms.persistence.model.FormsPackage;
import gov.ca.cwds.forms.service.dto.FormsPackageDTO;
import gov.ca.cwds.forms.service.mapper.FormsPackageMapper;
import gov.ca.cwds.forms.web.rest.parameters.FormsPackageParameterObject;

/**
 * @author CWDS TPT-2 Team
 */
public class FormsPackageService extends
    TypedCrudServiceAdapter<FormsPackageParameterObject, FormsPackageDTO, FormsPackageDTO> {

  private FormsPackageDao dao;

  @Inject
  private FormsPackageMapper formsPackageMapper;

  @Inject
  public FormsPackageService(FormsPackageDao dao) {
    this.dao = dao;
  }

  @Override
  public FormsPackageDTO create(FormsPackageDTO dto) {
    FormsPackage entity = formsPackageMapper.toEntity(dto);
    FormsPackageDTO formsPackageDTO = formsPackageMapper.toDTO(dao.create(entity));
    // May be form instances should be created as well

    return formsPackageDTO;
  }

  @Override
  public FormsPackageDTO find(FormsPackageParameterObject params) {
    if (params.getId() != null) {
      return formsPackageMapper.toDTO(dao.find(params.getId()));
    } else {
      throw new IllegalArgumentException("Expected formsPackage Id");
    }
  }

  @Override
  public FormsPackageDTO update(FormsPackageParameterObject params, FormsPackageDTO dto) {
    FormsPackage entity = formsPackageMapper.toEntity(dto);
    entity.setId(params.getId());
    FormsPackageDTO formsPackageDTO = formsPackageMapper.toDTO(dao.update(entity));
    // May be form instances should be created as well
    return formsPackageDTO;
  }

  @Override
  public FormsPackageDTO delete(FormsPackageParameterObject params) {
    return formsPackageMapper.toDTO(dao.delete(params.getId()));
  }

}
