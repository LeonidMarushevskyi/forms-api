package gov.ca.cwds.forms.web.rest.helper;

import static io.dropwizard.testing.FixtureHelpers.fixture;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jackson.JsonLoader;
import gov.ca.cwds.forms.Constants.API;
import gov.ca.cwds.forms.service.dto.FormInstanceDTO;
import gov.ca.cwds.forms.service.dto.FormsPackageCollectionDTO;
import gov.ca.cwds.forms.service.dto.FormsPackageDTO;
import gov.ca.cwds.forms.web.rest.RestClientTestRule;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * @author CWDS TPT-2 Team
 */

public class FormsHelper {

  private RestClientTestRule clientTestRule;

  private FormSchemaHelper schemaHelper;

  private ObjectMapper objectMapper = new ObjectMapper();

  public FormsHelper(RestClientTestRule clientTestRule) {
    this.clientTestRule = clientTestRule;
    schemaHelper = new FormSchemaHelper(clientTestRule);
  }

  public FormInstanceDTO createForm(String name, String schemaVersion) throws Exception {
    String testFormContent = fixture("fixtures/testForm-good.json");
    return createForm(name, schemaVersion, testFormContent);
  }

  public FormInstanceDTO buildFormInstanceDTO(String formName, String schemaVersion)
      throws Exception {
    String testFormSchema = fixture("fixtures/testForm-schema.json");
    schemaHelper.createFormsSchemaIfNotExist(formName, schemaVersion, testFormSchema);
    String testFormContent = fixture("fixtures/testForm-good.json");
    return buildForm(formName, schemaVersion, testFormContent);
  }



  public FormsPackageDTO buildFormsPackageDTO(String extId) {
    FormsPackageDTO formsPackageDTO = new FormsPackageDTO();
    formsPackageDTO.setDescription("Description");
    formsPackageDTO.setStatus("DRAFT");
    formsPackageDTO.setExternalEntityId(extId);

    return formsPackageDTO;
  }


  public FormInstanceDTO postForm(FormInstanceDTO form) {
    WebTarget target = clientTestRule.target(API.FORMS_INSTANCES_PATH);
    return target.request(MediaType.APPLICATION_JSON)
        .post(Entity.entity(form, MediaType.APPLICATION_JSON_TYPE), FormInstanceDTO.class);
  }

  public FormsPackageDTO postFormsPackage(FormsPackageDTO formsPackageDTO) {
    WebTarget target = clientTestRule.target(API.FORMS_PACKAGES_PATH);
    return target.request(MediaType.APPLICATION_JSON)
        .post(Entity.entity(formsPackageDTO, MediaType.APPLICATION_JSON_TYPE), FormsPackageDTO.class);
  }

  public FormsPackageDTO putFormsPackage(FormsPackageDTO formsPackageDTO) {
    WebTarget target = clientTestRule.target(API.FORMS_PACKAGES_PATH + "/" +formsPackageDTO.getId());
    return target.request(MediaType.APPLICATION_JSON)
        .put(Entity.entity(formsPackageDTO, MediaType.APPLICATION_JSON_TYPE), FormsPackageDTO.class);
  }

  private FormInstanceDTO buildForm(String name, String schemaVersion, String formContent)
      throws Exception {
    FormInstanceDTO form = new FormInstanceDTO();
    form.setName(name);
    form.setSchemaVersion(schemaVersion);
    JsonNode formContentJson = JsonLoader.fromString(formContent);
    form.setContent(formContentJson);
    return form;
  }

  public FormInstanceDTO createForm(String formName, String schemaVersion, String formContent)
      throws Exception {
    // Create schema for the form
    String testFormSchema = fixture("fixtures/testForm-schema.json");
    schemaHelper.createFormsSchema(formName, schemaVersion, testFormSchema);

    FormInstanceDTO formInstanceDTOBefore = buildForm(formName, schemaVersion, formContent);
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

    deleteSchema(instance.getName(), instance.getSchemaVersion());
  }

  public void deleteSchema(String name, String version) throws Exception {
    schemaHelper.deleteFormsSchema(name, version);
  }

  public FormsPackageCollectionDTO getFormsPackages(String extId) {
    WebTarget target = clientTestRule
        .target(API.FORMS_PACKAGES_PATH + "?extId=" + extId);
    return target.request(MediaType.APPLICATION_JSON).get(FormsPackageCollectionDTO.class);
  }
}
