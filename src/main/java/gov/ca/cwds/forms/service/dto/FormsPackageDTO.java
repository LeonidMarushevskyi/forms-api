package gov.ca.cwds.forms.service.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import gov.ca.cwds.rest.api.Request;
import gov.ca.cwds.rest.api.Response;
import java.util.List;

/**
 * @author CWDS TPT-2 Team
 */
@SuppressWarnings({"squid:S2160", "squid:S1948"})
//Default reflection hashcode and equals resides in BaseDTO
@SuppressFBWarnings("SE_BAD_FIELD")
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class FormsPackageDTO extends BaseDTO implements Request, Response {

  private Long id;

  private String externalEntityId;

  private String description;

  private List<FormInstanceDTO> formInstances;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getExternalEntityId() {
    return externalEntityId;
  }

  public void setExternalEntityId(String externalEntityId) {
    this.externalEntityId = externalEntityId;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public List<FormInstanceDTO> getFormInstances() {
    return formInstances;
  }

  public void setFormInstances(List<FormInstanceDTO> formInstances) {
    this.formInstances = formInstances;
  }
}
