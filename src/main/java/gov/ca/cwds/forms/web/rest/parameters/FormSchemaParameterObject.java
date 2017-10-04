package gov.ca.cwds.forms.web.rest.parameters;

import java.io.Serializable;

/**
 * @author CWDS TPT-2 Team
 */
public class FormSchemaParameterObject implements Serializable {

  private static final long serialVersionUID = -658056761734317794L;

  private String name;
  private String version;
  private Long id;

  public FormSchemaParameterObject(String name, String version) {
    this.name = name;
    this.version = version;
  }

  public FormSchemaParameterObject(String name, Long id) {
    this.name = name;
    this.id = id;
  }

  public FormSchemaParameterObject() {
  }

  public FormSchemaParameterObject(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getVersion() {
    return version;
  }

  public void setVersion(String version) {
    this.version = version;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

}
