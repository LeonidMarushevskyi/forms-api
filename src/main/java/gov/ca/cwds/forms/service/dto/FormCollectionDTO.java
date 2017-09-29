package gov.ca.cwds.forms.service.dto;

import java.util.Collection;

/**
 * @author CWDS TPT-2 Team
 */
public final class FormCollectionDTO extends CollectionDTO<FormInstanceDTO> {

  public FormCollectionDTO() {
  }

  public FormCollectionDTO(Collection<FormInstanceDTO> collection) {
    super(collection);
  }
}
