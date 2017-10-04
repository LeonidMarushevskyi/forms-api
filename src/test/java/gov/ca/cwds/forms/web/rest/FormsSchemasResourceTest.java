package gov.ca.cwds.forms.web.rest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import gov.ca.cwds.forms.BaseApiIntegrationTest;
import gov.ca.cwds.forms.Constants.API;
import gov.ca.cwds.forms.service.dto.FormSchemaCollectionDTO;
import gov.ca.cwds.forms.service.dto.FormSchemaDTO;
import gov.ca.cwds.forms.web.rest.helper.FormSchemaHelper;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author CWDS TPT-2 Team
 */

public class FormsSchemasResourceTest extends BaseApiIntegrationTest {

  private FormSchemaHelper schemaHelper = new FormSchemaHelper(clientTestRule);

  @BeforeClass
  public static void beforeClass() throws Exception {
    setUpFormsDb();
  }

  @Test
  public void getSchemaNotFound() throws Exception {
    WebTarget target = clientTestRule
        .target(API.FORMS_SCHEMAS_PATH + "/9999999");
    Response response = target.request(MediaType.APPLICATION_JSON).get();
    assertEquals(404, response.getStatus());
  }


  @Test
  public void getSchemaTest() throws Exception {
    String formName = "test_form1";
    String schemaVersion = "V1";
    FormSchemaDTO schema = schemaHelper.createFormsSchema(formName, schemaVersion);
    WebTarget target = clientTestRule
        .target(API.FORMS_SCHEMAS_PATH + "/" + schema.getFormSchemaId());
    Response response = target.request(MediaType.APPLICATION_JSON).get();
    FormSchemaDTO responseEntity = response.readEntity(FormSchemaDTO.class);
    assertEquals(200, response.getStatus());
    assertEquals(schema, responseEntity);
    // Delete test data
    schemaHelper.deleteFormsSchema(schema.getFormSchemaId());
  }

  @Test
  public void getAllSchemasTest() throws Exception {

    FormSchemaDTO schema1 = schemaHelper.createFormsSchema("test_form2", "V2");
    FormSchemaDTO schema2 = schemaHelper.createFormsSchema("test_form2", "V5");

    WebTarget target = clientTestRule
        .target(API.FORMS_SCHEMAS_PATH);
    Response response = target.request(MediaType.APPLICATION_JSON).get();
    FormSchemaCollectionDTO responseEntity = response.readEntity(FormSchemaCollectionDTO.class);
    assertEquals(200, response.getStatus());
    assertTrue(1 < responseEntity.getCollection().size());
    responseEntity.getCollection().forEach(System.out::println);

    // Delete test data
    schemaHelper.deleteFormsSchema(schema1.getFormSchemaId());
    schemaHelper.deleteFormsSchema(schema2.getFormSchemaId());
  }

  @Test
  public void updateSchemaTest() throws Exception {
    String formName = "test_form4";
    String schemaVersion = "V4";
    FormSchemaDTO schema = schemaHelper.createFormsSchema(formName, schemaVersion);
    schema.setDescription("new Description");
    WebTarget target = clientTestRule
        .target(API.FORMS_SCHEMAS_PATH + "/" + schema.getFormSchemaId());

    FormSchemaDTO updatedSchema = target
        .request(MediaType.APPLICATION_JSON)
        .put(Entity.entity(schema, MediaType.APPLICATION_JSON_TYPE), FormSchemaDTO.class);

    FormSchemaDTO fromServer = target.request(MediaType.APPLICATION_JSON)
        .get(FormSchemaDTO.class);

    assertEquals(schema, updatedSchema);
    assertEquals(schema, fromServer);
    assertEquals(updatedSchema, fromServer);

    // Delete test data
    schemaHelper.deleteFormsSchema(schema.getFormSchemaId());
  }

  @Test
  public void deleteSchemaTest() throws Exception {
    String formName = "test_form5";
    String schemaVersion = "V5";
    FormSchemaDTO schema = schemaHelper.createFormsSchema(formName, schemaVersion);
    WebTarget target = clientTestRule
        .target(API.FORMS_SCHEMAS_PATH + "/" + schema.getFormSchemaId());

    Response deleteResponse = target
        .request(MediaType.APPLICATION_JSON)
        .delete();

    Response getResponse = target.request(MediaType.APPLICATION_JSON).get();

    assertEquals(200, deleteResponse.getStatus());
    assertEquals(404, getResponse.getStatus());
  }

}
