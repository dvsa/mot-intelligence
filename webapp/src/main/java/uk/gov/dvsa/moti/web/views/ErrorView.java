package uk.gov.dvsa.moti.web.views;

import io.dropwizard.views.View;
import uk.gov.dvsa.moti.web.core.TemplatePaths;

/**
 * Display error page depending on HTTP response code
 */
public class ErrorView extends View {
    public ErrorView(String httpCode) {
        super(TemplatePaths.PACKAGE_ERROR + httpCode + TemplatePaths.EXTENSION);
    }
}
