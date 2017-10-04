package gov.ca.cwds.forms.web.rest.helper;

import static io.dropwizard.testing.FixtureHelpers.fixture;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import gov.ca.cwds.forms.Constants.API;
import gov.ca.cwds.forms.service.dto.FormInstanceDTO;
import gov.ca.cwds.forms.web.rest.RestClientTestRule;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * @author CWDS TPT-2 Team
 */

public class FormsHelper {

  public static final String TEST_FORM_NAME = "test_form";

  private RestClientTestRule clientTestRule;

  private FormSchemaHelper schemaHelper;

  public FormsHelper(RestClientTestRule clientTestRule) {
    this.clientTestRule = clientTestRule;
    schemaHelper = new FormSchemaHelper(clientTestRule);
  }

  public FormInstanceDTO createForm(String name, String schemaVersion) throws Exception {
    String testFormContent = fixture("fixtures/testForm-good.json");
    return createForm(name, schemaVersion, testFormContent);
  }

  public FormInstanceDTO postForm(FormInstanceDTO form) {
    WebTarget target = clientTestRule.target(API.FORMS_INSTANCES_PATH);
    return target.request(MediaType.APPLICATION_JSON)
        .post(Entity.entity(form, MediaType.APPLICATION_JSON_TYPE), FormInstanceDTO.class);
  }

  public FormInstanceDTO getForm(String name, String schemaVersion, String formContent)
      throws Exception {
    FormInstanceDTO form = new FormInstanceDTO();
    form.setName(name);
    form.setSchemaVersion(schemaVersion);
    form.setContent(formContent);
    return form;
  }

  public FormInstanceDTO createForm(String formName, String schemaVersion, String formContent)
      throws Exception {
    // Create schema for the form
    String testFormSchema = fixture("fixtures/testForm-schema.json");
    schemaHelper.createFormsSchema(formName, schemaVersion, testFormSchema);

    FormInstanceDTO formInstanceDTOBefore = getForm(formName, schemaVersion, formContent);
    FormInstanceDTO formInstanceDTOAfter = postForm(formInstanceDTOBefore);

    assertNotNull(formInstanceDTOAfter);
    assertEquals(formName, formInstanceDTOAfter.getName());
    assertEquals(schemaVersion, formInstanceDTOAfter.getSchemaVersion());
    return formInstanceDTOAfter;
  }

  public void deleteFormInstanceAndSchema(FormInstanceDTO instance) throws Exception {
    WebTarget target = clientTestRule
        .target(API.FORMS_INSTANCES_PATH + "/" + instance.getName() + "/" + instance.getFormId());
    Response deleteResponse = target.request(MediaType.APPLICATION_JSON).delete();

    schemaHelper.deleteFormsSchema(instance.getName(), instance.getSchemaVersion());
  }
}
