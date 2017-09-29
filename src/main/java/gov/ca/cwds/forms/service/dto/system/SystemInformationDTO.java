package gov.ca.cwds.forms.service.dto.system;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import gov.ca.cwds.forms.service.dto.BaseDTO;

/**
 * @author CWDS CALS API Team
 */
@SuppressWarnings("squid:S2160")
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class SystemInformationDTO extends BaseDTO {

  private String application;
  private String version;
  private String buildNumber;
  private HealthCheckResultDTO formsDb;
  private HealthCheckResultDTO deadlocks;

  public String getApplication() {
    return application;
  }

  public void setApplication(String application) {
    this.application = application;
  }

  public String getVersion() {
    return version;
  }

  public void setVersion(String version) {
    this.version = version;
  }

  public String getBuildNumber() {
    return buildNumber;
  }

  public void setBuildNumber(String buildNumber) {
    this.buildNumber = buildNumber;
  }

  public HealthCheckResultDTO getFormsDb() {
    return formsDb;
  }

  public void setFormsDb(HealthCheckResultDTO formsDb) {
    this.formsDb = formsDb;
  }

  public HealthCheckResultDTO getDeadlocks() {
    return deadlocks;
  }

  public void setDeadlocks(HealthCheckResultDTO deadlocks) {
    this.deadlocks = deadlocks;
  }
}
