package gov.ca.cwds.forms.exceptions;

import com.github.fge.jsonschema.core.report.ProcessingReport;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

/**
 * @author CWDS TPT-2 Team
 */
@SuppressWarnings("squid:S1948")
@SuppressFBWarnings("SE_BAD_FIELD")
public class FormInstanceValidationException extends RuntimeException {

  private static final long serialVersionUID = -6616692496235125080L;

  private final ProcessingReport report;

  public FormInstanceValidationException(ProcessingReport report) {
    super(String.valueOf(report));
    this.report = report;
  }

  public ProcessingReport getReport() {
    return report;
  }

}
