package uk.gov.dvsa.moti.web.views;

import io.dropwizard.views.View;

public class ErrorView extends View {

    private String errorId;

    public ErrorView(String httpCode) {
        super("core/error/" + httpCode + ".hbs");
    }

    public String getErrorId() {
        return errorId;
    }

    public ErrorView setErrorId(String errorId) {
        this.errorId = errorId;

        return this;
    }
}
