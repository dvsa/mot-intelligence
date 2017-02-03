package uk.gov.dvsa.moti.web.views;

import io.dropwizard.views.View;
import uk.gov.dvsa.moti.web.form.FraudForm;
import uk.gov.dvsa.moti.web.form.element.AbstractFormElement;
import uk.gov.dvsa.moti.web.form.element.FormElementOptions;
import uk.gov.dvsa.moti.web.model.FraudModel;

public class FraudFormView extends View {
    private String backLink = "http://www.gov.uk/";
    private FraudForm form;

    public FraudFormView(FraudForm form) {
        super("fraud/form.hbs");
        this.form = form;
    }

    public AbstractFormElement getVehicleReg() {
        FormElementOptions options = new FormElementOptions();
        options
                .setLabel("Registration")
                .setId(FraudModel.PARAM_VEHICLE_REG)
                .setCssClass("form-control-1-4")
                .markAsOptional()
        ;

        AbstractFormElement element = form.getElement(FraudModel.PARAM_VEHICLE_REG);
        element.setOptions(options);

        return element;
    }

    public AbstractFormElement getLocationName() {
        FormElementOptions options = new FormElementOptions();
        options
                .setLabel("Name of the garage")
                .setId(FraudModel.PARAM_LOCATION_NAME)
                .markAsOptional()
        ;

        AbstractFormElement element = form.getElement(FraudModel.PARAM_LOCATION_NAME);
        element.setOptions(options);

        return element;
    }

    public AbstractFormElement getLocationAddress() {
        FormElementOptions options = new FormElementOptions();
        options
                .setLabel("Address")
                .setId(FraudModel.PARAM_LOCATION_ADDRESS)
                .setHint("Include at least the street and town")
                .markAsOptional()
        ;

        AbstractFormElement element = form.getElement(FraudModel.PARAM_LOCATION_ADDRESS);
        element.setOptions(options);

        return element;
    }

    public AbstractFormElement getPersonName() {
        FormElementOptions options = new FormElementOptions();
        options
                .setLabel("Name")
                .setId(FraudModel.PARAM_PERSON_NAME)
                .markAsOptional()
        ;

        AbstractFormElement element = form.getElement(FraudModel.PARAM_PERSON_NAME);
        element.setOptions(options);

        return element;
    }

    public AbstractFormElement getPersonAddress() {
        FormElementOptions options = new FormElementOptions();
        options
                .setLabel("Address")
                .setId(FraudModel.PARAM_PERSON_ADDRESS)
                .setHint("Include at least the street and town")
                .markAsOptional()
        ;

        AbstractFormElement element = form.getElement(FraudModel.PARAM_PERSON_ADDRESS);
        element.setOptions(options);

        return element;
    }

    public AbstractFormElement getComments() {
        FormElementOptions options = new FormElementOptions();
        options
                .setLabel("Give a detailed description of what happened")
                .setId(FraudModel.PARAM_COMMENTS)
        ;

        AbstractFormElement element = form.getElement(FraudModel.PARAM_COMMENTS);
        element.setOptions(options);

        return element;
    }

    public String getBackLink() {
        return backLink;
    }
}
