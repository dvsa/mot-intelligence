package uk.gov.dvsa.moti.web.views;

import io.dropwizard.views.View;

import uk.gov.dvsa.moti.web.form.FraudForm;
import uk.gov.dvsa.moti.web.form.element.AbstractFormElement;
import uk.gov.dvsa.moti.web.form.element.HiddenElement;
import uk.gov.dvsa.moti.web.model.FraudModel;
import uk.gov.dvsa.moti.web.views.model.SummaryTable;

/**
 * Report fraud summary page
 */
public class FraudSummaryView extends View {
    private FraudModel model;
    private String backLink;
    private String csrfToken;

    public FraudSummaryView(FraudModel model, String backLink) {
        super("fraud/summary.hbs");
        this.model = model;
        this.backLink = backLink;
    }

    public SummaryTable getVehicleTable() {
        SummaryTable table = new SummaryTable("About the vehicle");
        table.addRow("Registration number", model.getVehicleReg());

        return table;
    }

    public SummaryTable getPersonTable() {
        SummaryTable table = new SummaryTable("About the person committing the fraud");
        table.addRow("Name", model.getPersonName());
        table.addRow("Address", model.getPersonAddress());

        return table;
    }

    public SummaryTable getLocationTable() {
        SummaryTable table = new SummaryTable("About the location");
        table.addRow("Name of the garage", model.getLocationName());
        table.addRow("Address", model.getLocationAddress());

        return table;
    }

    public SummaryTable getDescriptionTable() {
        SummaryTable table = new SummaryTable("About the incident");
        table.addRow("Incident description", model.getComments());

        return table;
    }

    public String getBackLink() {
        return backLink;
    }

    public String getCsrfToken() {
        return csrfToken;
    }

    public void setCsrfToken(String csrfToken) {
        this.csrfToken = csrfToken;
    }

    public AbstractFormElement getCsrfTokenElement() {
        return new HiddenElement(FraudModel.PARAM_CSRF, this.csrfToken);
    }
}
