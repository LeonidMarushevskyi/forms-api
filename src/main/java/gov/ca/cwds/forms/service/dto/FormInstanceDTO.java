package gov.ca.cwds.forms.service.dto;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import gov.ca.cwds.rest.api.Request;
import gov.ca.cwds.rest.api.Response;
import javax.validation.constraints.NotNull;

/**
 * @author CWDS TPT-2 Team
 */
@SuppressWarnings({"squid:S2160", "squid:S1948"})
//Default reflection hashcode and equals resides in BaseDTO
@SuppressFBWarnings("SE_BAD_FIELD")
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class FormInstanceDTO extends BaseDTO implements Request, Response {

  private static final long serialVersionUID = -8643054699905680948L;

  private Long formId;

  @NotNull
  private String name;

  @NotNull
  private String schemaVersion;

  private Long parentFormId;

  private String parentFormName;

  private Long packageId;

  @NotNull
  private JsonNode content;

  public Long getFormId() {
    return formId;
  }

  public void setFormId(Long formId) {
    this.formId = formId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getSchemaVersion() {
    return schemaVersion;
  }

  public void setSchemaVersion(String schemaVersion) {
    this.schemaVersion = schemaVersion;
  }

  public Long getParentFormId() {
    return parentFormId;
  }

  public void setParentFormId(Long parentFormId) {
    this.parentFormId = parentFormId;
  }

  public String getParentFormName() {
    return parentFormName;
  }

  public void setParentFormName(String parentFormName) {
    this.parentFormName = parentFormName;
  }

  public Long getPackageId() {
    return packageId;
  }

  public void setPackageId(Long packageId) {
    this.packageId = packageId;
  }

  public JsonNode getContent() {
    return content;
  }

  public void setContent(JsonNode content) {
    this.content = content;
  }
}
