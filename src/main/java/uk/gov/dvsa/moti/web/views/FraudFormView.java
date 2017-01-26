package uk.gov.dvsa.moti.web.views;

import io.dropwizard.views.View;
import uk.gov.dvsa.moti.web.form.FraudForm;
import uk.gov.dvsa.moti.web.form.element.FormElement;
import uk.gov.dvsa.moti.web.model.FraudModel;

public class FraudFormView extends View {
    private FraudForm form;

    public FraudFormView(FraudForm form) {
        super("fraud/form.hbs");
        this.form = form;
    }

    public FormElement getVehicleReg() {
        return form.getElement(FraudModel.PARAM_VEHICLE_REG);
    }

    public FormElement getVehicleMake() {
        return form.getElement(FraudModel.PARAM_VEHICLE_MAKE);
    }

    public FormElement getVehicleModel() { return form.getElement(FraudModel.PARAM_VEHICLE_MODEL); }

    public FormElement getLocationName() {
        return form.getElement(FraudModel.PARAM_LOCATION_NAME);
    }

    public FormElement getLocationAddress() {
        return form.getElement(FraudModel.PARAM_LOCATION_ADDRESS);
    }

    public FormElement getPersonName() {
        return form.getElement(FraudModel.PARAM_PERSON_NAME);
    }

    public FormElement getPersonAddress() {
        return form.getElement(FraudModel.PARAM_PERSON_ADDRESS);
    }

    public FormElement getComments() {
        return form.getElement(FraudModel.PARAM_COMMENTS);
    }
}
