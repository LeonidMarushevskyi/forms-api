package gov.ca.cwds.forms;

/**
 * @author CWDS TPT-2 Team
 */
public final class Constants {

  public static final String Y = "Y";

  public static final String N = "N";

  public static final String SPACE = " ";

  public static final String SQL_TYPE = "sqlType";

  public static final String RETURNED_CLASS_NAME_PARAM = "returnedClassName";

  private Constants() {
  }

  public static class API {

    public static final String SYSTEM_INFORMATION_PATH = "system-information";

    public static final String FORMS_SCHEMAS_PATH = "forms/schemas";
    public static final String FORM_SCHEMA_TAG = "Forms Schemas";

    public static final String FORMS_INSTANCES_PATH = "forms/instances";
    public static final String FORM_STATUS = "status";
    public static final String FORMS_INSTANCE_TAG = "Forms Instances ";

    private API() {
    }

    public static class PathParams {

      public static final String FORM_SCHEMA_ID_PARAMETER = "formSchemaId";

      public static final String FORM_NAME_PARAMETER = "formName";
      public static final String FORM_ID_PARAMETER = "formId";


      private PathParams() {
      }
    }
  }

  public static class UnitOfWork {

    public static final String FORMS_UNIT_OF_WORK = "formsUnitOfWork";

    private UnitOfWork() {
    }
  }

  public static class ExpectedExceptionMessages {

    private ExpectedExceptionMessages() {
    }

  }

  public static class Validation {

    private Validation() {
    }
  }
}
