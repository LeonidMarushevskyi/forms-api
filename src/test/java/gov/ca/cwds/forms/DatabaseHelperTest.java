package gov.ca.cwds.forms;

import java.util.HashMap;
import java.util.Map;
import liquibase.exception.LiquibaseException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author CWDS TPT-2 Team
 */

public class DatabaseHelperTest extends BaseApiIntegrationTest {

  DatabaseHelper helper;

  @BeforeClass
  public static void beforeClass() throws Exception {
    setUpFormsDb();
  }

  @Before
  public void before() {
    helper = getFormsDatabaseHelper();
  }

  @Test
  public void runScript() throws Exception {
    Map<String, Object> params = new HashMap<>();
    helper.runScript("liquibase/test_database_master.xml", params, null);

    try {
      helper.runScript("liquibase/test_database_master.xml", params, "");
      Assert.fail();
    } catch (LiquibaseException e) {
      // expected
    }
  }

  @Test
  public void runScript1() throws Exception {
    helper.runScript("liquibase/test_database_master2.xml", null);
    try {
      helper.runScript("liquibase/test_database_master2.xml", "");
      Assert.fail();
    } catch (LiquibaseException e) {
      // expected
    }
  }

}