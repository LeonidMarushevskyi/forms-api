package gov.ca.cwds.forms.web.rest;

import static io.dropwizard.testing.FixtureHelpers.fixture;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import gov.ca.cwds.forms.BaseApiIntegrationTest;
import gov.ca.cwds.forms.Constants.API;
import gov.ca.cwds.forms.service.dto.FormCollectionDTO;
import gov.ca.cwds.forms.service.dto.FormInstanceDTO;
import gov.ca.cwds.forms.web.rest.helper.FormsHelper;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author CWDS TPT-2 Team
 */
public class FormsInstancesResourceTest extends BaseApiIntegrationTest {

  protected FormsHelper formsHelper = new FormsHelper(clientTestRule);

  @BeforeClass
  public static void beforeClass() throws Exception {
    setUpFormsDb();
  }

  @Test
  public void getFormNotFound() throws Exception {
    WebTarget target = clientTestRule
        .target(API.FORMS_INSTANCES_PATH + "/FakeName/9999999");
    Response response = target.request(MediaType.APPLICATION_JSON).get();
    assertEquals(404, response.getStatus());
  }


  @Test
  public void getFormInstanceTest() throws Exception {
    String formName = "test_form";
    String schemaVersion = "V1";

    FormInstanceDTO formInstance = formsHelper.createForm(formName, schemaVersion);

    WebTarget target = clientTestRule
        .target(API.FORMS_INSTANCES_PATH + "/" + formName + "/" + formInstance.getFormId());
    Response response = target.request(MediaType.APPLICATION_JSON).get();
    FormInstanceDTO responseEntity = response.readEntity(FormInstanceDTO.class);
    assertEquals(200, response.getStatus());
    assertEquals(formInstance, responseEntity);
  }

  @Test
  public void getAllFormInstancesTest() throws Exception {

    FormInstanceDTO schema1 = formsHelper.createForm("test_form1", "V1");
    FormInstanceDTO schema2 = formsHelper.createForm("test_form2", "V2");

    WebTarget target = clientTestRule
        .target(API.FORMS_INSTANCES_PATH);
    Response response = target.request(MediaType.APPLICATION_JSON).get();
    FormCollectionDTO responseEntity = response.readEntity(FormCollectionDTO.class);
    assertEquals(200, response.getStatus());
    assertTrue(1 < responseEntity.getCollection().size());
    responseEntity.getCollection().forEach(System.out::println);
  }

  @Test
  public void updateFormInstanceTest() throws Exception {
    String formName = "test_form";
    String schemaVersion = "V2";
    FormInstanceDTO schema = formsHelper.createForm(formName, schemaVersion);
    schema.setParentFormId(1L);
    WebTarget target = clientTestRule
        .target(API.FORMS_INSTANCES_PATH + "/" + formName + "/" + schema.getFormId());

    FormInstanceDTO updatedSchema = target
        .request(MediaType.APPLICATION_JSON)
        .put(Entity.entity(schema, MediaType.APPLICATION_JSON_TYPE), FormInstanceDTO.class);

    FormInstanceDTO fromServer = target.request(MediaType.APPLICATION_JSON)
        .get(FormInstanceDTO.class);

    assertEquals(schema, updatedSchema);
    assertEquals(schema, fromServer);
    assertEquals(updatedSchema, fromServer);
  }

  @Test
  public void deleteSchemaTest() throws Exception {
    String formName = "test_form";
    String schemaVersion = "V3";
    FormInstanceDTO schema = formsHelper.createForm(formName, schemaVersion);
    WebTarget target = clientTestRule
        .target(API.FORMS_INSTANCES_PATH + "/" + formName + "/" + schema.getFormId());

    Response deleteResponse = target
        .request(MediaType.APPLICATION_JSON)
        .delete();

    Response getResponse = target.request(MediaType.APPLICATION_JSON).get();

    assertEquals(200, deleteResponse.getStatus());
    assertEquals(404, getResponse.getStatus());
  }

  @Test
  public void invalidFormTest() {
    String formName = "test_form";
    String schemaVersion = "V5";

    String formContent = fixture("fixtures/testForm-no-required.json");

    try {
      FormInstanceDTO schema = formsHelper.createForm(formName, schemaVersion, formContent);
      fail();
    } catch (Exception e) {
      // Do nothing. expected behaviour
    }

  }
}
