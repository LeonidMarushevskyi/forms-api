package gov.ca.cwds.forms.service.mapper;

import gov.ca.cwds.forms.persistence.model.FormsPackage;
import gov.ca.cwds.forms.service.dto.FormsPackageDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author CWDS TPT-2 Team
 */
@SuppressWarnings("squid:S1214")
@Mapper
public interface FormsPackageMapper {
  FormsPackageMapper INSTANCE = Mappers.getMapper(FormsPackageMapper.class);


  FormsPackage toEntity(FormsPackageDTO dto);


  FormsPackageDTO toDTO(FormsPackage entity);
}
