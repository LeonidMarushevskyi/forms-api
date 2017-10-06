package gov.ca.cwds.forms.service.dto;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import gov.ca.cwds.rest.api.Request;
import gov.ca.cwds.rest.api.Response;

/**
 * @author CWDS TPT-2 Team
 */
@SuppressWarnings({"squid:S2160", "squid:S1948"})
@SuppressFBWarnings("SE_BAD_FIELD")
//Default reflection hashcode and equals resides in BaseDTO
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class FormSchemaDTO extends BaseDTO implements Request, Response {

  private static final long serialVersionUID = -8838258019659004221L;

  private Long formSchemaId;

  private String formName;

  private String schemaVersion;

  private String description;

  private JsonNode jsonSchema;

  public Long getFormSchemaId() {
    return formSchemaId;
  }

  public void setFormSchemaId(Long formSchemaId) {
    this.formSchemaId = formSchemaId;
  }

  public String getFormName() {
    return formName;
  }

  public void setFormName(String formName) {
    this.formName = formName;
  }

  public String getSchemaVersion() {
    return schemaVersion;
  }

  public void setSchemaVersion(String schemaVersion) {
    this.schemaVersion = schemaVersion;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public JsonNode getJsonSchema() {
    return jsonSchema;
  }

  public void setJsonSchema(JsonNode jsonSchema) {
    this.jsonSchema = jsonSchema;
  }
}
