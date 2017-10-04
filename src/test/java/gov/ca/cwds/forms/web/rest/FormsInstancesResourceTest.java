package gov.ca.cwds.forms.web.rest;

import static io.dropwizard.testing.FixtureHelpers.fixture;
import static org.junit.Assert.assertEquals;
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

    String formName = "test_form1";
    FormInstanceDTO instance1 = formsHelper.createForm(formName, "V2");
    FormInstanceDTO instance2 = formsHelper.createForm(formName, "V3");

    WebTarget target = clientTestRule
        .target(API.FORMS_INSTANCES_PATH + "/" + formName);
    Response response = target.request(MediaType.APPLICATION_JSON).get();
    FormCollectionDTO responseEntity = response.readEntity(FormCollectionDTO.class);
    assertEquals(200, response.getStatus());
    assertEquals(2, responseEntity.getCollection().size());
    responseEntity.getCollection().forEach(System.out::println);
  }

  @Test
  public void updateFormInstanceTest() throws Exception {
    String formName = "test_form";
    String schemaVersion = "V4";
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
    String schemaVersion = "V5";
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
    String schemaVersion = "V6";

    String formContent = fixture("fixtures/testForm-no-required.json");

    try {
      FormInstanceDTO schema = formsHelper.createForm(formName, schemaVersion, formContent);
      fail();
    } catch (Exception e) {
      // Do nothing. expected behaviour
    }

  }
}
