package gov.ca.cwds.forms.web.rest.helper;

import static io.dropwizard.testing.FixtureHelpers.fixture;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jackson.JsonLoader;
import gov.ca.cwds.forms.Constants.API;
import gov.ca.cwds.forms.service.dto.FormSchemaDTO;
import gov.ca.cwds.forms.web.rest.RestClientTestRule;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * @author CWDS TPT-2 Team
 */

public class FormSchemaHelper {

  private RestClientTestRule clientTestRule;

  private ObjectMapper objectMapper = new ObjectMapper();

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
    JsonNode schemaJson = JsonLoader.fromString(jsonSchema);
    schema.setJsonSchema(schemaJson);
    // Fill form here
    return schema;
  }

  public FormSchemaDTO createFormsSchema(String formName, String schemaVersion, String jsonSchema)
      throws Exception {
    deleteFormsSchema(formName, schemaVersion);
    FormSchemaDTO schemaDTOBefore = getSchema(formName, schemaVersion, jsonSchema);
    FormSchemaDTO schemaDTOAfter = postSchema(schemaDTOBefore);
    assertNotNull(schemaDTOAfter);
    assertEquals(formName, schemaDTOAfter.getFormName());
    assertEquals(schemaVersion, schemaDTOAfter.getSchemaVersion());
    return schemaDTOAfter;
  }

  public void deleteFormsSchema(Long schemaId) throws Exception {
    WebTarget target = clientTestRule
        .target(API.FORMS_SCHEMAS_PATH + "/" + schemaId);
    Response deleteResponse = target.request(MediaType.APPLICATION_JSON).delete();
  }

  public void deleteFormsSchema(String name, String schemaVersion) throws Exception {
    WebTarget target = clientTestRule
        .target(API.FORMS_SCHEMAS_PATH + "/" + name + "/" + schemaVersion);
    Response response = target.request(MediaType.APPLICATION_JSON).get();
    if (404 == response.getStatus()) {
      return;
    }
    FormSchemaDTO schema = response.readEntity(FormSchemaDTO.class);
    deleteFormsSchema(schema.getFormSchemaId());
  }
}
