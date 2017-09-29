package gov.ca.cwds.forms.web.rest.system;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import gov.ca.cwds.forms.BaseApiIntegrationTest;
import gov.ca.cwds.forms.Constants;
import gov.ca.cwds.forms.service.dto.system.HealthCheckResultDTO;
import gov.ca.cwds.forms.service.dto.system.SystemInformationDTO;
import java.io.IOException;
import javax.ws.rs.core.MediaType;
import org.junit.Test;

public class SystemInformationResourceTest extends BaseApiIntegrationTest {

  @Test
  public void testSystemInformationGet() throws IOException {
    SystemInformationDTO systemInformationDTO = clientTestRule
        .target(Constants.API.SYSTEM_INFORMATION_PATH).
            request(MediaType.APPLICATION_JSON).get(SystemInformationDTO.class);
    assertEquals("CWDS FORMS API", systemInformationDTO.getApplication());
    assertNotNull(systemInformationDTO.getVersion());
    assertNotNull(systemInformationDTO.getDeadlocks());
    assertDataSource(systemInformationDTO.getFormsDb());
  }

  public void assertDataSource(HealthCheckResultDTO healthCheckResultDTO) {
    assertNotNull(healthCheckResultDTO);
    assertTrue(healthCheckResultDTO.isHealthy());
  }
}