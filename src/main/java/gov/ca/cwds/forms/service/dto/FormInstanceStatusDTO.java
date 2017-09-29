package gov.ca.cwds.forms.service.dto;

/**
 * @author CWDS TPT-2 Team
 */
@SuppressWarnings("squid:S2160")//Default reflection hashcode and equals resides in BaseDTO
public class FormInstanceStatusDTO extends BaseDTO {

  private static final long serialVersionUID = 554013994622454267L;

  private FormInstanceStatus status;

  public FormInstanceStatusDTO() {
  }

  public FormInstanceStatusDTO(FormInstanceStatus status) {
    this.status = status;
  }

  public FormInstanceStatus getStatus() {
    return status;
  }

  public void setStatus(FormInstanceStatus status) {
    this.status = status;
  }
}
