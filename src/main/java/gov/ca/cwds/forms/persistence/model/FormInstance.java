package gov.ca.cwds.forms.persistence.model;

import com.fasterxml.jackson.databind.JsonNode;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import gov.ca.cwds.data.persistence.PersistentObject;
import gov.ca.cwds.forms.service.dto.FormInstanceStatus;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
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
@NamedQuery(name = FormInstance.NAMED_QUERY_FIND_ALL, query = "FROM FormInstance ORDER BY id DESC")
@NamedQuery(
    name = FormInstance.NAMED_QUERY_FIND_BY_FORM_NAME,
    query = "FROM FormInstance f WHERE f.name = :formName ORDER BY id DESC")

@Entity
@Table(name = "form_instance")
public class FormInstance implements PersistentObject {

  public static final String NAMED_QUERY_FIND_BY_FORM_NAME =
      "gov.ca.cwds.forms.persistence.model.FormInstance.find.by.formName";

  public static final String NAMED_QUERY_FIND_ALL =
      "gov.ca.cwds.forms.persistence.model.FormInstance.find.all";
  private static final long serialVersionUID = 8433047669468840813L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotNull
  @Column(name = "form_name", length = 32, nullable = false)
  private String name;

  @NotNull
  @Column(name = "schema_version", length = 10, nullable = false)
  private String schemaVersion;

  @Column(name = "parent_form_id")
  private Long parentFormId;

  @Column(name = "parent_form_name", length = 32)
  private String parentFormName;

  @Column(name = "status")
  @Enumerated
  private FormInstanceStatus status;

  @Type(type = "FormInstanceType")
  @Column(name = "content")
  private JsonNode content;


  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getSchemaVersion() {
    return schemaVersion;
  }

  public void setSchemaVersion(String schemaVersion) {
    this.schemaVersion = schemaVersion;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
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

  public FormInstanceStatus getStatus() {
    return status;
  }

  public void setStatus(FormInstanceStatus status) {
    this.status = status;
  }

  public JsonNode getContent() {
    return content;
  }

  public void setContent(JsonNode content) {
    this.content = content;
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


  @Override
  public Serializable getPrimaryKey() {
    return getId();
  }
}
