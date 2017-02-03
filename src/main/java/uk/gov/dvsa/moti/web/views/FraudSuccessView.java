package uk.gov.dvsa.moti.web.views;

import io.dropwizard.views.View;

public class FraudSuccessView extends View {
    private String continueLink = "http://www.gov.uk/";

    public FraudSuccessView() {
        super("fraud/success.hbs");
    }

    public String getContinueLink() {
        return continueLink;
    }
}
