package gov.ca.cwds.forms.web.rest;

import static gov.ca.cwds.forms.Constants.API.FORMS_INSTANCE_TAG;
import static gov.ca.cwds.forms.Constants.API.FORMS_SCHEMAS_PATH;
import static gov.ca.cwds.forms.Constants.API.FORM_SCHEMA_TAG;
import static gov.ca.cwds.forms.Constants.API.PathParams.FORM_SCHEMA_ID_PARAMETER;
import static gov.ca.cwds.forms.Constants.UnitOfWork.FORMS_UNIT_OF_WORK;

import com.codahale.metrics.annotation.Timed;
import com.google.inject.Inject;
import gov.ca.cwds.forms.inject.FormsSchemasCollectionServiceBackedResource;
import gov.ca.cwds.forms.inject.FormsSchemasServiceBackedResource;
import gov.ca.cwds.forms.service.dto.FormSchemaCollectionDTO;
import gov.ca.cwds.forms.service.dto.FormSchemaDTO;
import gov.ca.cwds.forms.web.rest.parameters.FormSchemaParameterObject;
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
@Api(tags = {FORM_SCHEMA_TAG})
@Path(FORMS_SCHEMAS_PATH)
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class FormsSchemasResource {

  private TypedResourceDelegate<FormSchemaParameterObject, FormSchemaDTO> resourceDelegate;
  private ResourceDelegate collectionResourceDelegate;

  @Inject
  public FormsSchemasResource(
      @FormsSchemasServiceBackedResource
          TypedResourceDelegate<FormSchemaParameterObject, FormSchemaDTO> resourceDelegate,
      @FormsSchemasCollectionServiceBackedResource
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
  @ApiOperation(value = "Creates and returns Form Schema", response = FormSchemaDTO.class)
  public Response createFormSchema(
      @ApiParam(name = "formSchema", value = "The FormSchema object")
      @Valid
          FormSchemaDTO formSchema) {
    return resourceDelegate.create(formSchema);
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
  @ApiOperation(value = "Returns All available Form Schemas", response = FormSchemaCollectionDTO.class)
  public Response getAllFormsSchemas() {
    return collectionResourceDelegate.get(true);
  }

  @UnitOfWork(FORMS_UNIT_OF_WORK)
  @GET
  @Path("/{" + FORM_SCHEMA_ID_PARAMETER + "}")
  @Timed
  @ApiResponses(
      value = {
          @ApiResponse(code = 401, message = "Not Authorized"),
          @ApiResponse(code = 404, message = "Not found"),
          @ApiResponse(code = 406, message = "Accept Header not supported")
      }
  )
  @ApiOperation(value = "Returns Form Schema by Id", response = FormSchemaDTO.class)
  public Response getFormSchema(
      @PathParam(FORM_SCHEMA_ID_PARAMETER)
      @ApiParam(required = true, name = FORM_SCHEMA_ID_PARAMETER, value = "Form Schema Id")
          Long formSchemaId) {
    return resourceDelegate.get(new FormSchemaParameterObject(formSchemaId));
  }

  @UnitOfWork(FORMS_UNIT_OF_WORK)
  @PUT
  @Path("/{" + FORM_SCHEMA_ID_PARAMETER + "}")
  @Timed
  @ApiResponses(
      value = {
          @ApiResponse(code = 400, message = "Bad request"),
          @ApiResponse(code = 404, message = "Not Found"),
          @ApiResponse(code = 401, message = "Not Authorized"),
          @ApiResponse(code = 406, message = "Accept Header not supported")
      }
  )
  @ApiOperation(value = "Updates Form Schema", response = FormSchemaDTO.class)
  public Response updateFormSchema(
      @PathParam(FORM_SCHEMA_ID_PARAMETER)
      @ApiParam(required = true, name = FORM_SCHEMA_ID_PARAMETER, value = "Form Schema Id")
          Long formSchemaId,
      @ApiParam(name = "form", value = "The Form object")
      @Valid
          FormSchemaDTO formSchemaDTO) {
    return resourceDelegate.update(new FormSchemaParameterObject(formSchemaId), formSchemaDTO);
  }

  @UnitOfWork(FORMS_UNIT_OF_WORK)
  @DELETE
  @Path("/{" + FORM_SCHEMA_ID_PARAMETER + "}")
  @Timed
  @ApiResponses(
      value = {
          @ApiResponse(code = 400, message = "Bad request"),
          @ApiResponse(code = 404, message = "Not Found"),
          @ApiResponse(code = 401, message = "Not Authorized"),
          @ApiResponse(code = 406, message = "Accept Header not supported")
      }
  )
  @ApiOperation(value = "Delete Form Schema", response = FormSchemaDTO.class)
  public Response deleteFormSchema(
      @PathParam(FORM_SCHEMA_ID_PARAMETER)
      @ApiParam(required = true, name = FORM_SCHEMA_ID_PARAMETER, value = "Form Schema Id")
          Long formSchemaId
  ) {
    return resourceDelegate.delete(new FormSchemaParameterObject(formSchemaId));
  }

}
