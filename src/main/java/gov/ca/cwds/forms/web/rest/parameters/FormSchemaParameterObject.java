package gov.ca.cwds.forms.web.rest.parameters;

import java.io.Serializable;

/**
 * @author CWDS TPT-2 Team
 */
public class FormSchemaParameterObject implements Serializable {

  private static final long serialVersionUID = -658056761734317794L;

  private String name;
  private long id;

  public FormSchemaParameterObject(String name, Long id) {
    this.name = name;
    this.id = id;
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

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

}
