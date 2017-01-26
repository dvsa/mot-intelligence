package uk.gov.dvsa.moti.web.form;

import uk.gov.dvsa.moti.web.model.FraudModel;

import javax.validation.ConstraintViolation;
import java.util.Set;

public class FraudForm extends AbstractForm {

    private FraudModel model;
    private boolean isValid = true;

    public FraudForm(FraudModel model) {
        super();

        this.model = model;

        addElement(FraudModel.PARAM_VEHICLE_REG, model.getVehicleReg());
        addElement(FraudModel.PARAM_VEHICLE_MAKE, model.getVehicleMake());
        addElement(FraudModel.PARAM_VEHICLE_MODEL, model.getVehicleModel());
        addElement(FraudModel.PARAM_LOCATION_NAME, model.getLocationName());
        addElement(FraudModel.PARAM_LOCATION_ADDRESS, model.getLocationAddress());
        addElement(FraudModel.PARAM_PERSON_NAME, model.getPersonName());
        addElement(FraudModel.PARAM_PERSON_ADDRESS, model.getPersonAddress());
        addElement(FraudModel.PARAM_COMMENTS, model.getComments());
    }

    public boolean isValid() {

        formElements.forEach((name, element)->{
            Set<ConstraintViolation<FraudModel>> constraintViolations = validator.validateProperty(model, name);
            if (constraintViolations.size() > 0) {
                element.setErrorMessage(constraintViolations.iterator().next().getMessage());
                isValid = false;
            }
        });

        return isValid;
    }
}
