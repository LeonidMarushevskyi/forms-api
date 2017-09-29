package gov.ca.cwds.forms.web.rest;

import static gov.ca.cwds.forms.Constants.API.FORMS_INSTANCES_PATH;
import static gov.ca.cwds.forms.Constants.API.FORMS_INSTANCE_TAG;
import static gov.ca.cwds.forms.Constants.API.FORM_STATUS;
import static gov.ca.cwds.forms.Constants.API.PathParams.FORM_ID_PARAMETER;
import static gov.ca.cwds.forms.Constants.API.PathParams.FORM_NAME_PARAMETER;
import static gov.ca.cwds.forms.Constants.UnitOfWork.FORMS_UNIT_OF_WORK;

import com.codahale.metrics.annotation.Timed;
import com.google.inject.Inject;
import gov.ca.cwds.forms.inject.FormsCollectionServiceBackedResource;
import gov.ca.cwds.forms.inject.FormsServiceBackedResource;
import gov.ca.cwds.forms.service.dto.FormCollectionDTO;
import gov.ca.cwds.forms.service.dto.FormInstanceDTO;
import gov.ca.cwds.forms.service.dto.FormInstanceStatusDTO;
import gov.ca.cwds.forms.web.rest.parameters.FormParameterObject;
import gov.ca.cwds.rest.resources.ResourceDelegate;
import gov.ca.cwds.rest.resources.TypedResourceDelegate;
import io.dropwizard.hibernate.UnitOfWork;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * @author CWDS TPT-2 Team
 */
@Api(tags = {FORMS_INSTANCE_TAG})
@Path(FORMS_INSTANCES_PATH)
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class FormsInstancesResource {

  private TypedResourceDelegate<FormParameterObject, FormInstanceDTO> resourceDelegate;
  private ResourceDelegate collectionResourceDelegate;

  @Inject
  public FormsInstancesResource(
      @FormsServiceBackedResource
          TypedResourceDelegate<FormParameterObject, FormInstanceDTO> resourceDelegate,
      @FormsCollectionServiceBackedResource
          ResourceDelegate collectionResourceDelegate) {
    this.resourceDelegate = resourceDelegate;
    this.collectionResourceDelegate = collectionResourceDelegate;
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
  @ApiOperation(value = "Creates and returns Form Instance", response = FormInstanceDTO.class)
  public Response createFormInstance(
      @ApiParam(name = "form", value = "The Form object")
      @Valid
          FormInstanceDTO form) {
    return resourceDelegate.create(form);
  }

  @UnitOfWork(FORMS_UNIT_OF_WORK)
  @GET
  @PathParam("/{" + FORM_NAME_PARAMETER + "}")
  @Timed
  @ApiResponses(
      value = {
          @ApiResponse(code = 401, message = "Not Authorized"),
          @ApiResponse(code = 404, message = "Not found"),
          @ApiResponse(code = 406, message = "Accept Header not supported")
      }
  )
  @ApiOperation(value = "Returns All available Form Instances for name", response = FormCollectionDTO.class)
  public Response getAllForms(
      @PathParam(FORM_NAME_PARAMETER)
      @ApiParam(required = true, name = FORM_NAME_PARAMETER, value = "Form Name")
          String formName
  ) {
    return collectionResourceDelegate.get(new FormParameterObject(formName));
  }

  @UnitOfWork(FORMS_UNIT_OF_WORK)
  @GET
  @Path("/{" + FORM_NAME_PARAMETER + "}" + "/{" + FORM_ID_PARAMETER + "}")
  @Timed
  @ApiResponses(
      value = {
          @ApiResponse(code = 401, message = "Not Authorized"),
          @ApiResponse(code = 404, message = "Not found"),
          @ApiResponse(code = 406, message = "Accept Header not supported")
      }
  )
  @ApiOperation(value = "Returns Form by name and Id", response = FormInstanceDTO.class)
  public Response getForm(
      @PathParam(FORM_NAME_PARAMETER)
      @ApiParam(required = true, name = FORM_NAME_PARAMETER, value = "Form Name")
          String formName,

      @PathParam(FORM_ID_PARAMETER)
      @ApiParam(required = true, name = FORM_ID_PARAMETER, value = "Form Instance Id")
          Long formId) {
    return resourceDelegate.get(new FormParameterObject(formName, formId));
  }

  @UnitOfWork(FORMS_UNIT_OF_WORK)
  @PUT
  @Path("/{" + FORM_NAME_PARAMETER + "}" + "/{" + FORM_ID_PARAMETER + "}")
  @Timed
  @ApiResponses(
      value = {
          @ApiResponse(code = 400, message = "Bad request"),
          @ApiResponse(code = 404, message = "Not Found"),
          @ApiResponse(code = 401, message = "Not Authorized"),
          @ApiResponse(code = 406, message = "Accept Header not supported")
      }
  )
  @ApiOperation(value = "Updates Form Schema", response = FormInstanceDTO.class)
  public Response updateForm(
      @PathParam(FORM_NAME_PARAMETER)
      @ApiParam(required = true, name = FORM_NAME_PARAMETER, value = "Form Name")
          String formName,

      @PathParam(FORM_ID_PARAMETER)
      @ApiParam(required = true, name = FORM_ID_PARAMETER, value = "Form Instance Id")
          Long formId,

      @ApiParam(name = "form", value = "The Form object")
      @Valid
          FormInstanceDTO formInstanceDTO) {
    return resourceDelegate.update(new FormParameterObject(formName, formId), formInstanceDTO);
  }

  @UnitOfWork(FORMS_UNIT_OF_WORK)
  @DELETE
  @Path("/{" + FORM_NAME_PARAMETER + "}" + "/{" + FORM_ID_PARAMETER + "}")
  @Timed
  @ApiResponses(
      value = {
          @ApiResponse(code = 400, message = "Bad request"),
          @ApiResponse(code = 404, message = "Not Found"),
          @ApiResponse(code = 401, message = "Not Authorized"),
          @ApiResponse(code = 406, message = "Accept Header not supported")
      }
  )
  @ApiOperation(value = "Delete Form Instance", response = FormInstanceDTO.class)
  public Response deleteForm(
      @PathParam(FORM_NAME_PARAMETER)
      @ApiParam(required = true, name = FORM_NAME_PARAMETER, value = "Form Name")
          String formName,

      @PathParam(FORM_ID_PARAMETER)
      @ApiParam(required = true, name = FORM_ID_PARAMETER, value = "Form Instance Id")
          Long formId
  ) {
    return resourceDelegate.delete(new FormParameterObject(formName, formId));
  }


  @UnitOfWork(FORMS_UNIT_OF_WORK)
  @PUT
  @Path("/{" + FORM_NAME_PARAMETER + "}" + "/{" + FORM_ID_PARAMETER + "}/" + FORM_STATUS)
  @Timed
  @ApiResponses(
      value = {
          @ApiResponse(code = 400, message = "Bad request"),
          @ApiResponse(code = 404, message = "Not Found"),
          @ApiResponse(code = 401, message = "Not Authorized"),
          @ApiResponse(code = 406, message = "Accept Header not supported")
      }
  )
  @ApiOperation(value = "Updates Form Status", response = FormInstanceStatusDTO.class)
  public Response updateFormInstanceStatus(
      @PathParam(FORM_NAME_PARAMETER)
      @ApiParam(required = true, name = FORM_NAME_PARAMETER, value = "Form Name")
          String formName,

      @PathParam(FORM_ID_PARAMETER)
      @ApiParam(required = true, name = FORM_ID_PARAMETER, value = "Form Instance Id")
          Long formId,

      @ApiParam(required = true, name = FORM_STATUS, value = "Form Instance Status")
          FormInstanceStatusDTO status
  ) {
    throw new UnsupportedOperationException();
  }

}
