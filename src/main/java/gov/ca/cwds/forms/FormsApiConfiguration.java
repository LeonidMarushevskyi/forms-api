package gov.ca.cwds.forms;

import com.fasterxml.jackson.annotation.JsonProperty;
import gov.ca.cwds.rest.MinimalApiConfiguration;
import io.dropwizard.db.DataSourceFactory;

public class FormsApiConfiguration extends MinimalApiConfiguration {

  private DataSourceFactory formsDataSourceFactory;

  private boolean upgardeDbOnStart = false;

  @JsonProperty
  public DataSourceFactory getFormsDataSourceFactory() {
    return formsDataSourceFactory;
  }

  public void setFormsDataSourceFactory(DataSourceFactory formsDataSourceFactory) {
    this.formsDataSourceFactory = formsDataSourceFactory;
  }

  @JsonProperty
  public boolean isUpgradeDbOnStart() {
    return upgardeDbOnStart;
  }

  public void setUpgardeDbOnStart(boolean upgardeDbOnStart) {
    this.upgardeDbOnStart = upgardeDbOnStart;
  }

}
