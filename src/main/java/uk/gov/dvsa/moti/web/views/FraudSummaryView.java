package uk.gov.dvsa.moti.web.views;

import io.dropwizard.views.View;

public class FraudSummaryView extends View{
    public FraudSummaryView() {
        super("fraud/summary.hbs");
    }
}
