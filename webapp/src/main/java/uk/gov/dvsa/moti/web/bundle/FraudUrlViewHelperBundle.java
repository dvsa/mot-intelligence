package uk.gov.dvsa.moti.web.bundle;

import io.codemonastery.dropwizard.views.handlebars.HandlebarsHelperBundle;
import io.dropwizard.Configuration;
import uk.gov.dvsa.moti.web.bundle.helper.CookiePolicyUrlViewHelper;

public class FraudUrlViewHelperBundle extends HandlebarsHelperBundle {

    @Override
    protected void configureHandlebars(Configuration configuration) {
        registerHelper(CookiePolicyUrlViewHelper.NAME, new CookiePolicyUrlViewHelper());
    }
}
