package gov.ca.cwds.forms;

import static com.google.common.io.Resources.getResource;

import java.io.IOException;
import java.io.StringWriter;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;

/**
 * @author CWDS TPT-2 Team
 */
public class DictionaryLiquibaseGenerator {

  private static final String TABLE_NAME = "state_type";

  public static void main(String[] args) throws IOException, URISyntaxException {
    VelocityEngine ve = new VelocityEngine();
    ve.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
    ve.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());

    ve.init();
    VelocityContext context = new VelocityContext();

    context.put("tableName", TABLE_NAME);
    Template template = null;
    template = ve.getTemplate("LegacyComplaintDictionaryLiquibaseTemplate.vm");

    context.put("dictItems", readItems());

    StringWriter sw = new StringWriter();
    template.merge(context, sw);
    sw.flush();
    System.out.println(sw);
    sw.close();
  }


  private static List<DictItem> readItems() throws IOException, URISyntaxException {
    String csvFile = "DictItems.csv";
    String line = "";
    String cvsSplitBy = ",";
    URL url = getResource(csvFile);
    System.out.printf("URL:" + url);
    Path path = Paths.get(url.toURI());
    System.out.printf("Path:" + path.toString());
    List<String> lines = Files
        .readAllLines(path);
    List<DictItem> items = new ArrayList<>();
    lines.forEach(s -> {
      String[] values = s.split(cvsSplitBy);
      items.add(new DictItem(values[0], values[1], values[2]));
    });
    return items;
  }

  public static class DictItem {

    private String cwsId;
    private String lisId;
    private String value;

    public DictItem(String cwsId, String lisId, String value) {
      this.cwsId = cwsId;
      this.lisId = lisId;
      this.value = value;
    }

    public String getCwsId() {
      return cwsId;
    }

    public void setCwsId(String cwsId) {
      this.cwsId = cwsId;
    }

    public String getLisId() {
      return lisId;
    }

    public void setLisId(String lisId) {
      this.lisId = lisId;
    }

    public String getValue() {
      return value;
    }

    public void setValue(String value) {
      this.value = value;
    }
  }

}
