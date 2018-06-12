package gov.ca.cwds.forms.web.rest;

import static org.junit.Assert.assertEquals;

import gov.ca.cwds.forms.BaseApiIntegrationTest;
import gov.ca.cwds.forms.Constants.API;
import gov.ca.cwds.forms.service.dto.FormInstanceDTO;
import gov.ca.cwds.forms.service.dto.FormsPackageCollectionDTO;
import gov.ca.cwds.forms.service.dto.FormsPackageDTO;
import gov.ca.cwds.forms.web.rest.helper.FormsHelper;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author CWDS TPT-2 Team
 */
public class FormsPackageResourceTest extends BaseApiIntegrationTest {

  protected FormsHelper formsHelper = new FormsHelper(clientTestRule);

  @BeforeClass
  public static void beforeClass() throws Exception {
    setUpFormsDb();
  }

  @Test
  public void getPackagesNotFound() throws Exception {
    WebTarget target = clientTestRule
        .target(API.FORMS_PACKAGES_PATH + "?extId=123");
    Response response = target.request(MediaType.APPLICATION_JSON).get();
    assertEquals(404, response.getStatus());
  }

  @Test
  public void createEmptyFormsPackageTest() throws Exception {
    FormsPackageDTO formsPackageDTO = formsHelper.buildFormsPackageDTO("123asd");
    FormsPackageDTO formsPackageDTOstored = formsHelper.postFormsPackage(formsPackageDTO);

    Assert.assertEquals(formsPackageDTO, formsPackageDTOstored);

  }

  @Test
  public void saveFormsPackageWithInstancesTest() throws Exception {

    String extId = "321dsa";

    FormsPackageDTO formsPackageDTOstored = postFormsPackage(extId);

    FormsPackageCollectionDTO formsPackages = formsHelper.getFormsPackages(extId);

    FormsPackageDTO formsPackage = formsPackages.getCollection().stream()
        .filter(f -> formsPackageDTOstored.getId().equals(f.getId())).findFirst().get();

    Assert.assertEquals(2, formsPackage.getFormInstances().size());

  }

  @Test
  public void updateFormsPackageWithInstancesTest() throws Exception {

    String extId = "098qwe";
    FormsPackageDTO formsPackageDTOstored = postFormsPackage(extId);

    FormsPackageCollectionDTO formsPackages = formsHelper.getFormsPackages(extId);

    FormsPackageDTO formsPackage = formsPackages.getCollection().stream()
        .filter(f -> formsPackageDTOstored.getId().equals(f.getId())).findFirst().get();

    String parentFormName = "sss";
    formsPackage.getFormInstances().get(0).setFormId(null);
    formsPackage.getFormInstances().forEach(formInstanceDTO -> {
      formInstanceDTO.setParentFormName(parentFormName);
    });

    formsHelper.putFormsPackage(formsPackage);

    FormsPackageDTO updatedPackage = formsHelper.getFormsPackages(extId).getCollection().stream()
        .filter(f -> formsPackageDTOstored.getId().equals(f.getId())).findFirst().get();
    ;

    Assert.assertEquals(2, updatedPackage.getFormInstances().size());

    formsPackage.getFormInstances().forEach(formInstanceDTO -> {
      Assert.assertEquals(parentFormName, formInstanceDTO.getParentFormName());
    });

  }

  private FormsPackageDTO postFormsPackage(String extId) throws Exception {
    FormsPackageDTO formsPackageDTO = formsHelper.buildFormsPackageDTO(extId);

    List<FormInstanceDTO> instances = new ArrayList<>();
    instances.add(formsHelper.buildFormInstanceDTO("instance_for_package_1", "1"));
    instances.add(formsHelper.buildFormInstanceDTO("instance_for_package_2", "1"));

    formsPackageDTO.setFormInstances(instances);

    return formsHelper.postFormsPackage(formsPackageDTO);
  }


}
