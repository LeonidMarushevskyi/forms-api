package gov.ca.cwds.forms.web.rest.parameters;

import java.io.Serializable;

/**
 * @author CWDS TPT-2 Team
 */
public class FormsPackageParameterObject implements Serializable {

  private static final long serialVersionUID = -8865367106194046468L;

  private Long id;
  private String exernalId;
  private String status;


  public FormsPackageParameterObject(Long id, String externalId, String status) {
    this(externalId, status);
    this.id = id;
  }

  public FormsPackageParameterObject(String externalId, String status) {
    this.exernalId = externalId;
    this.status = status;
  }

  public FormsPackageParameterObject() {
  }

  public FormsPackageParameterObject(Long id) {
    this.id = id;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getExernalId() {
    return exernalId;
  }

  public void setExernalId(String exernalId) {
    this.exernalId = exernalId;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }
}
