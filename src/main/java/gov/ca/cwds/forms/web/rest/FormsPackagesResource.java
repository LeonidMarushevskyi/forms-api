package gov.ca.cwds.forms.web.rest;

import static gov.ca.cwds.forms.Constants.API.FORMS_PACKAGES_PATH;
import static gov.ca.cwds.forms.Constants.API.FORMS_PACKAGES_TAG;
import static gov.ca.cwds.forms.Constants.API.PathParams.FORMS_PACKAGE_EXT_ID_PARAM;
import static gov.ca.cwds.forms.Constants.API.PathParams.FORMS_PACKAGE_ID_PARAMETER;
import static gov.ca.cwds.forms.Constants.API.PathParams.FORMS_PACKAGE_STATUS_PARAM;
import static gov.ca.cwds.forms.Constants.UnitOfWork.FORMS_UNIT_OF_WORK;

import com.codahale.metrics.annotation.Timed;
import gov.ca.cwds.forms.inject.FormsPackageCollectionServiceBackedResource;
import gov.ca.cwds.forms.inject.FormsPackageServiceBackedResource;
import gov.ca.cwds.forms.service.dto.FormsPackageCollectionDTO;
import gov.ca.cwds.forms.service.dto.FormsPackageDTO;
import gov.ca.cwds.forms.web.rest.parameters.FormsPackageParameterObject;
import gov.ca.cwds.rest.resources.ResourceDelegate;
import gov.ca.cwds.rest.resources.TypedResourceDelegate;
import io.dropwizard.hibernate.UnitOfWork;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * @author CWDS TPT-2 Team
 */
@Api(tags = {FORMS_PACKAGES_TAG})
@Path(FORMS_PACKAGES_PATH)
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class FormsPackagesResource {

  private TypedResourceDelegate<FormsPackageParameterObject, FormsPackageDTO> resourceDelegate;
  private ResourceDelegate collectionResourceDelegate;

  @Inject
  public FormsPackagesResource(
      @FormsPackageServiceBackedResource
          TypedResourceDelegate<FormsPackageParameterObject, FormsPackageDTO> resourceDelegate,
      @FormsPackageCollectionServiceBackedResource
          ResourceDelegate collectionResourceDelegate) {
    this.resourceDelegate = resourceDelegate;
    this.collectionResourceDelegate = collectionResourceDelegate;
  }

  @UnitOfWork(FORMS_UNIT_OF_WORK)
  @GET
  @Timed
  @ApiResponses(
      value = {
          @ApiResponse(code = 401, message = "Not Authorized"),
          @ApiResponse(code = 404, message = "Not found"),
          @ApiResponse(code = 406, message = "Accept Header not supported")
      }
  )
  @ApiOperation(value = "Returns Forms Packages", response = FormsPackageCollectionDTO.class)
  public Response getFormsPackages(
      @QueryParam(FORMS_PACKAGE_EXT_ID_PARAM)
      @ApiParam(name = FORMS_PACKAGE_EXT_ID_PARAM, value = "External entity Id") String extId,
      @QueryParam(FORMS_PACKAGE_STATUS_PARAM)
      @ApiParam(name = FORMS_PACKAGE_STATUS_PARAM, value = "Package Status", allowableValues = "SUBMITTED, INPROGRESS") String status) {
    return collectionResourceDelegate.get(new FormsPackageParameterObject(extId, status));
  }

  @UnitOfWork(FORMS_UNIT_OF_WORK)
  @POST
  @Timed
  @ApiResponses(
      value = {
          @ApiResponse(code = 201, message = "Created"),
          @ApiResponse(code = 401, message = "Not Authorized"),
          @ApiResponse(code = 406, message = "Accept Header not supported")
      }
  )
  @ApiOperation(value = "Creates and returns Forms Package", response = FormsPackageDTO.class)
  public Response createFormsPackage(
      @ApiParam(name = "formsPackage", value = "The FormSchema object")
      @Valid
          FormsPackageDTO formsPackage) {
    return resourceDelegate.create(formsPackage);
  }

  @UnitOfWork(FORMS_UNIT_OF_WORK)
  @PUT
  @Path("/{" + FORMS_PACKAGE_ID_PARAMETER + "}")
  @Timed
  @ApiResponses(
      value = {
          @ApiResponse(code = 400, message = "Bad request"),
          @ApiResponse(code = 404, message = "Not Found"),
          @ApiResponse(code = 401, message = "Not Authorized"),
          @ApiResponse(code = 406, message = "Accept Header not supported")
      }
  )
  @ApiOperation(value = "Updates Forms Package", response = FormsPackageDTO.class)
  public Response updateFormsPackage(
      @PathParam(FORMS_PACKAGE_ID_PARAMETER)
      @ApiParam(required = true, name = FORMS_PACKAGE_ID_PARAMETER, value = "Forms Package Id")
          Long formsPackageId,
      @ApiParam(name = "formsPackage", value = "The Forms Package object")
      @Valid
          FormsPackageDTO formsPackageDTO) {
    return resourceDelegate.update(new FormsPackageParameterObject(formsPackageId), formsPackageDTO);
  }

}
