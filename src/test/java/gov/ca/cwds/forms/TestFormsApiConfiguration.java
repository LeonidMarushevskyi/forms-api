package gov.ca.cwds.forms;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.db.DataSourceFactory;

/**
 * @author CWDS TPT-2 Team
 */

public class TestFormsApiConfiguration extends FormsApiConfiguration {

  private DataSourceFactory cmsrsDataSourceFactory;

  @JsonProperty
  public DataSourceFactory getCmsrsDataSourceFactory() {
    return cmsrsDataSourceFactory;
  }

  public void setCmsnsDataSourceFactory(DataSourceFactory cmsnsDataSourceFactory) {
    this.cmsrsDataSourceFactory = cmsnsDataSourceFactory;
  }
}
