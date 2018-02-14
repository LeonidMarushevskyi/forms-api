package gov.ca.cwds.forms.service.mapper;

import gov.ca.cwds.forms.persistence.model.FormInstance;
import gov.ca.cwds.forms.persistence.model.FormsPackage;
import gov.ca.cwds.forms.service.dto.FormInstanceDTO;
import gov.ca.cwds.forms.service.dto.FormsPackageDTO;
import java.util.List;
import java.util.stream.Collectors;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * @author CWDS TPT-2 Team
 */
@SuppressWarnings({"squid:S1214", "squid:S1168"})
@Mapper
public interface FormsPackageMapper {

  FormsPackageMapper INSTANCE = Mappers.getMapper(FormsPackageMapper.class);

  @Mapping(target = "id", source = "id")
  @Mapping(target = "externalEntityId", source = "externalEntityId")
  @Mapping(target = "description", source = "description")
  @Mapping(target = "status", source = "status")
  FormsPackage toEntity(FormsPackageDTO dto);

  @Mapping(target = "id", source = "id")
  @Mapping(target = "externalEntityId", source = "externalEntityId")
  @Mapping(target = "description", source = "description")
  @Mapping(target = "status", source = "status")
  FormsPackageDTO toDTO(FormsPackage entity);


  default List<FormInstanceDTO> toDTOList(List<FormInstance> entities) {
    if (entities == null || entities.isEmpty()) {
      return null;
    }
    return entities.stream().map(FormInstanceMapper.INSTANCE::toFormDTO)
        .collect(Collectors.toList());
  }

  default List<FormInstance> toEntityList(List<FormInstanceDTO> dtos) {
    if (dtos == null || dtos.isEmpty()) {
      return null;
    }
    return dtos.stream().map(FormInstanceMapper.INSTANCE::toForm)
        .collect(Collectors.toList());
  }


}
