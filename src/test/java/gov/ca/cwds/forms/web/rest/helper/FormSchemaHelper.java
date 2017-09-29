package gov.ca.cwds.forms.web.rest.helper;

import static io.dropwizard.testing.FixtureHelpers.fixture;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import gov.ca.cwds.forms.Constants.API;
import gov.ca.cwds.forms.service.dto.FormSchemaDTO;
import gov.ca.cwds.forms.web.rest.RestClientTestRule;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

/**
 * @author CWDS TPT-2 Team
 */

public class FormSchemaHelper {

  public static final String TEST_FORM_NAME = "test_form";

  private RestClientTestRule clientTestRule;

  public FormSchemaHelper(RestClientTestRule clientTestRule) {
    this.clientTestRule = clientTestRule;
  }

  public FormSchemaDTO createFormsSchema(String formName, String schemaVersion) throws Exception {
    return createFormsSchema(formName, schemaVersion, fixture("fixtures/testForm-schema.json"));
  }

  public FormSchemaDTO postSchema(FormSchemaDTO schema) {
    WebTarget target = clientTestRule.target(API.FORMS_SCHEMAS_PATH);
    return target.request(MediaType.APPLICATION_JSON)
        .post(Entity.entity(schema, MediaType.APPLICATION_JSON_TYPE), FormSchemaDTO.class);
  }

  public FormSchemaDTO getSchema(String name, String schemaVersion, String jsonSchema)
      throws Exception {
    FormSchemaDTO schema = new FormSchemaDTO();
    schema.setFormName(name);
    schema.setSchemaVersion(schemaVersion);
    schema.setJsonSchema(jsonSchema);
    // Fill form here
    return schema;
  }

  public FormSchemaDTO createFormsSchema(String formName, String schemaVersion, String jsonSchema)
      throws Exception {
    FormSchemaDTO schemaDTOBefore = getSchema(formName, schemaVersion, jsonSchema);
    FormSchemaDTO schemaDTOAfter = postSchema(schemaDTOBefore);
    assertNotNull(schemaDTOAfter);
    assertEquals(formName, schemaDTOAfter.getFormName());
    assertEquals(schemaVersion, schemaDTOAfter.getSchemaVersion());
    return schemaDTOAfter;
  }
}
