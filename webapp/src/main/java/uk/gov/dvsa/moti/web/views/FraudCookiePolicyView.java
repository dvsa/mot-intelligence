package uk.gov.dvsa.moti.web.views;

import io.dropwizard.views.View;

public class FraudCookiePolicyView extends View {

    public FraudCookiePolicyView() {
        super("fraud/cookie_policy.hbs");
    }
}
