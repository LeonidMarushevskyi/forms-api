package gov.ca.cwds.forms.persistence.model;

import com.fasterxml.jackson.databind.JsonNode;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import gov.ca.cwds.data.persistence.PersistentObject;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.annotations.NamedQuery;
import org.hibernate.annotations.Type;

/**
 * @author CWDS TPT-2 Team
 */
@SuppressWarnings("squid:S1948") //JsonNode is serializable
@SuppressFBWarnings("SE_BAD_FIELD")
@NamedQuery(name = FormSchema.NAMED_QUERY_FIND_ALL, query = "FROM FormSchema ORDER BY id DESC")
@NamedQuery(
    name = FormSchema.NAMED_QUERY_FIND_BY_NAME_AND_VERSION,
    query = "FROM FormSchema fs WHERE fs.formName = :formName AND fs.schemaVersion = :schemaVersion"
)

@Entity
@Table(name = "form_schema")
public class FormSchema implements PersistentObject {

  public static final String NAMED_QUERY_FIND_ALL =
      "gov.ca.cwds.forms.persistence.model.FormSchema.find.all";
  public static final String NAMED_QUERY_FIND_BY_NAME_AND_VERSION =
      "gov.ca.cwds.forms.persistence.model.FormSchema.find.byNameAndVersion";
  private static final long serialVersionUID = -6343976123252131912L;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotNull
  @Column(name = "form_name", length = 32, nullable = false)
  private String formName;

  @NotNull
  @Column(name = "schema_version", length = 10, nullable = false)
  private String schemaVersion;

  @Column(name = "description")
  private String description;

  @NotNull
  @Type(type = "FormSchemaType")
  @Column(name = "json_schema", nullable = false)
  private JsonNode jsonSchema;

  @Type(type = "FormSchemaType")
  @Column(name = "ui_schema")
  private JsonNode uiSchema;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
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

  public JsonNode getUiSchema() {
    return uiSchema;
  }

  public void setUiSchema(JsonNode uiSchema) {
    this.uiSchema = uiSchema;
  }

  @Override
  public Serializable getPrimaryKey() {
    return id;
  }

  @Override
  public boolean equals(Object o) {
    return EqualsBuilder
        .reflectionEquals(this, o);
  }

  @Override
  public int hashCode() {
    return HashCodeBuilder
        .reflectionHashCode(this);
  }

}
