package gov.ca.cwds.forms.service.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import gov.ca.cwds.rest.api.Request;
import gov.ca.cwds.rest.api.Response;
import java.util.List;
import java.util.Objects;

/**
 * @author CWDS TPT-2 Team
 */
@SuppressWarnings({"squid:S2160", "squid:S1948"})
//Default reflection hashcode and equals resides in BaseDTO
@SuppressFBWarnings("SE_BAD_FIELD")
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class FormsPackageDTO extends BaseDTO implements Request, Response {

  private static final long serialVersionUID = -919781566975359710L;
  
  private Long id;

  private String externalEntityId;

  private String description;

  private String status;

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

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public List<FormInstanceDTO> getFormInstances() {
    return formInstances;
  }

  public void setFormInstances(List<FormInstanceDTO> formInstances) {
    this.formInstances = formInstances;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof FormsPackageDTO)) {
      return false;
    }
    FormsPackageDTO that = (FormsPackageDTO) o;
    return Objects.equals(externalEntityId, that.externalEntityId) &&
        Objects.equals(description, that.description) &&
        Objects.equals(status, that.status);
  }

  @Override
  public int hashCode() {

    return Objects.hash(super.hashCode(), externalEntityId, description, status);
  }
}
