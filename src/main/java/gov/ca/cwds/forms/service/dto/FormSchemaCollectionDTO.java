package gov.ca.cwds.forms.service.dto;

import java.util.Collection;

/**
 * @author CWDS TPT-2 Team
 */
public final class FormSchemaCollectionDTO extends CollectionDTO<FormSchemaDTO> {

  public FormSchemaCollectionDTO() {
  }

  public FormSchemaCollectionDTO(Collection<FormSchemaDTO> collection) {
    super(collection);
  }
}
