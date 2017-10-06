package gov.ca.cwds.forms.persistence.hibernate;

import static gov.ca.cwds.forms.Constants.SQL_TYPE;
import static org.junit.Assert.assertTrue;

import java.util.Properties;
import org.junit.Test;

/**
 * @author CWDS TPT-2 Team
 */
public class JsonTypeTest {

  @Test
  public void setParameterValues() throws Exception {

    JsonType jsonType = new JsonType() {
      @Override
      public Class returnedClass() {
        return null;
      }
    };

    Properties properties = new Properties();
    properties.put(SQL_TYPE, SQLTypes.CLOB_TYPE_NAME);

    jsonType.setParameterValues(properties);

    try {
      properties.put(SQL_TYPE, "something not supported");
      jsonType.setParameterValues(properties);
      assertTrue(false);
    } catch (Exception e) {
      assertTrue(true);
    }

    assertTrue("{}".equals(jsonType.replace("{}", null, null)));
  }
}
