package gov.ca.cwds.forms.exceptions;

import com.github.fge.jsonschema.core.report.ProcessingReport;

/**
 * @author CWDS TPT-2 Team
 */
public class FormInstanceValidationException extends RuntimeException {

  private static final long serialVersionUID = -6616692496235125080L;

  private final transient ProcessingReport report;

  public FormInstanceValidationException(ProcessingReport report) {
    super(String.valueOf(report));
    this.report = report;
  }

  public ProcessingReport getReport() {
    return report;
  }

}
