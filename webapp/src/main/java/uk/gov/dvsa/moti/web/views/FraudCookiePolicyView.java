package uk.gov.dvsa.moti.web.views;

import io.dropwizard.views.View;

/**
 * Display fraud cookie policy page
 */
public class FraudCookiePolicyView extends View {

    public FraudCookiePolicyView() {
        super("fraud/cookie_policy.hbs");
    }
}
