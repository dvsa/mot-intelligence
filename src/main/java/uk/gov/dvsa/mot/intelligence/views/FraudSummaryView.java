package uk.gov.dvsa.mot.intelligence.views;

import io.dropwizard.views.View;

public class FraudSummaryView extends View{
    public FraudSummaryView() {
        super("fraud/summary.hbs");
    }
}
