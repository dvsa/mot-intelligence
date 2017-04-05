package uk.gov.dvsa.moti.web.views;

import io.dropwizard.views.View;

/**
 * Report fraud success page
 */
public class FraudSuccessView extends View {
    private String continueLink = "http://www.gov.uk/";

    public FraudSuccessView() {
        super("fraud/success.hbs");
    }

    public String getContinueLink() {
        return continueLink;
    }
}
