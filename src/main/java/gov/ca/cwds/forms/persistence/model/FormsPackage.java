package gov.ca.cwds.forms.persistence.model;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import gov.ca.cwds.data.persistence.PersistentObject;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.NamedQuery;

/**
 * @author CWDS TPT-2 Team
 */

@SuppressWarnings("squid:S1948") //JsonNode is serializable
@SuppressFBWarnings("SE_BAD_FIELD")
@NamedQuery(name = FormsPackage.NAMED_QUERY_FIND_ALL, query = "FROM FormsPackage ORDER BY id DESC")
@NamedQuery(
    name = FormsPackage.NAMED_QUERY_FIND_BY_EXT_ID,
    query = "FROM FormsPackage fs WHERE fs.externalEntityId = :extId"
)
@NamedQuery(
    name = FormsPackage.NAMED_QUERY_FIND_BY_EXT_ID_AND_STATUS,
    query = "FROM FormsPackage fs WHERE fs.externalEntityId = :extId AND fs.status= :status"
)

@Entity
@Table(name = "forms_package")
public class FormsPackage implements PersistentObject {

  private static final long serialVersionUID = -7533903448920642784L;

  public static final String NAMED_QUERY_FIND_ALL = "gov.ca.cwds.forms.persistence.model.find.all";
  public static final String NAMED_QUERY_FIND_BY_EXT_ID = "gov.ca.cwds.forms.persistence.model.find.by.extId";
  public static final String NAMED_QUERY_FIND_BY_EXT_ID_AND_STATUS = "gov.ca.cwds.forms.persistence.model.find.by.extId.and.status";

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name="ext_entity_id")
  private String externalEntityId;

  @Column(name="description")
  private String description;

  @Column(name="status", nullable = false)
  private String status;

  @OneToMany(fetch = FetchType.EAGER, mappedBy = "formsPackage")
  private List<FormInstance> formInstances;

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

  public List<FormInstance> getFormInstances() {
    return formInstances;
  }

  public void setFormInstances(
      List<FormInstance> formInstances) {
    this.formInstances = formInstances;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  @Override
  public Serializable getPrimaryKey() {
    return getId();
  }
}
