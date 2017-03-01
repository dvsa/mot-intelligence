package uk.gov.dvsa.moti.web.views;

import io.dropwizard.views.View;

public class HomeView extends View {

    private String formLink;

    public HomeView (String formLink) {
        super("fraud/home.hbs");

        this.formLink = formLink;
    }

    public String getFormLink() {
        return formLink;
    }
}
