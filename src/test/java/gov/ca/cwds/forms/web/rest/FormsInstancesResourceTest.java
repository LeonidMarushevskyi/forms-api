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

    //Cleanup DB
    formsHelper.deleteFormInstanceAndSchema(formInstance);
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

    //Cleanup DB
    formsHelper.deleteFormInstanceAndSchema(instance1);
    formsHelper.deleteFormInstanceAndSchema(instance2);
  }

  @Test
  public void updateFormInstanceTest() throws Exception {
    String formName = "test_form2";
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

    //Cleanup DB
    formsHelper.deleteFormInstanceAndSchema(fromServer);
  }

  @Test
  public void deleteInstanceTest() throws Exception {
    String formName = "test_form3";
    String schemaVersion = "V5";
    FormInstanceDTO instance = formsHelper.createForm(formName, schemaVersion);
    WebTarget target = clientTestRule
        .target(API.FORMS_INSTANCES_PATH + "/" + formName + "/" + instance.getFormId());
    Response deleteResponse = target.request(MediaType.APPLICATION_JSON).delete();
    Response getResponse = target.request(MediaType.APPLICATION_JSON).get();
    assertEquals(200, deleteResponse.getStatus());
    assertEquals(404, getResponse.getStatus());

    // Cleanup DB
    formsHelper.deleteSchema(formName, schemaVersion);
  }

  @Test
  public void invalidFormTest() throws Exception {
    String formName = "test_form4";
    String schemaVersion = "V6";

    String formContent = fixture("fixtures/testForm-no-required.json");

    try {
      FormInstanceDTO instance = formsHelper.createForm(formName, schemaVersion, formContent);
      fail();
    } catch (Exception e) {
      // Do nothing. expected behaviour
    }

    formsHelper.deleteSchema(formName, schemaVersion);
  }
}
