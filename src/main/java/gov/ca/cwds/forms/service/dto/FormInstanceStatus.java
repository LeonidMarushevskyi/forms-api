package gov.ca.cwds.forms.service.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author CWDS TPT-2 Team
 */
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum FormInstanceStatus {

  DRAFT("draft"),
  IN_PROGRESS("in-progress"),
  SUBMITTED("submitted");

  @ApiModelProperty(example = "submitted")
  private final String name;

  FormInstanceStatus(String name) {
    this.name = name;
  }

  @JsonValue
  public String getName() {
    return name;
  }

  public FormInstanceStatusDTO toDTO() {
    return new FormInstanceStatusDTO(this);
  }

}
