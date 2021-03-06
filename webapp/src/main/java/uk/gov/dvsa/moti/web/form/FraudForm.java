package uk.gov.dvsa.moti.web.form;

import uk.gov.dvsa.moti.web.form.element.TextAreaElement;
import uk.gov.dvsa.moti.web.form.element.TextElement;
import uk.gov.dvsa.moti.web.model.FraudModel;

import javax.validation.ConstraintViolation;
import java.util.Set;

/**
 * Report MOT Fraud form
 */
public class FraudForm extends AbstractForm {

    private FraudModel model;
    private boolean isValid = true;

    public FraudForm(FraudModel model) {
        super();

        this.model = model;

        addElement(new TextElement(FraudModel.PARAM_VEHICLE_REG, model.getVehicleReg()));
        addElement(new TextElement(FraudModel.PARAM_LOCATION_NAME, model.getLocationName()));
        addElement(new TextElement(FraudModel.PARAM_LOCATION_ADDRESS, model.getLocationAddress()));
        addElement(new TextElement(FraudModel.PARAM_PERSON_NAME, model.getPersonName()));
        addElement(new TextElement(FraudModel.PARAM_PERSON_ADDRESS, model.getPersonAddress()));
        addElement(new TextAreaElement(FraudModel.PARAM_COMMENTS, model.getComments()));
    }

    public boolean isValid() {

        formElements.forEach((element)->{
            Set<ConstraintViolation<FraudModel>> constraintViolations = validator.validateProperty(model, element.getName());
            if (constraintViolations.size() > 0) {
                element.setErrorMessage(constraintViolations.iterator().next().getMessage());
                isValid = false;
            }
        });

        return isValid;
    }
}
