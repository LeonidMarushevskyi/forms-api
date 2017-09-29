package gov.ca.cwds.forms.exceptions;

import com.github.fge.jsonschema.core.report.ProcessingReport;

/**
 * @author CWDS TPT-2 Team
 */
public class FormValidationException extends RuntimeException {

  public FormValidationException(ProcessingReport report) {
    super(String.valueOf(report));
  }

}
