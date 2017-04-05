package uk.gov.dvsa.moti.web.model;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import java.io.Serializable;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;

/**
 * Fraud model used in report fraud form
 */
public class FraudModel implements Serializable {
    public static final String PARAM_CSRF = "csrfToken";
    public static final String PARAM_VEHICLE_REG = "vehicleReg";
    public static final String PARAM_LOCATION_NAME = "locationName";
    public static final String PARAM_LOCATION_ADDRESS = "locationAddress";
    public static final String PARAM_PERSON_NAME = "personName";
    public static final String PARAM_PERSON_ADDRESS = "personAddress";
    public static final String PARAM_COMMENTS = "comments";

    public static final int SHORT_FIELD_MAX_LENGTH = 8;
    public static final int MAX_LENGTH = 60;
    public static final int COMMENT_FIELD_MAX_LENGTH = 2000;
    public static final String MAX_LENGTH_VALIDATION_MSG = "must be {max} characters or less";

    @Length(max = SHORT_FIELD_MAX_LENGTH, message = MAX_LENGTH_VALIDATION_MSG)
    @FormParam(PARAM_VEHICLE_REG)
    @DefaultValue("")
    private String vehicleReg;

    @Length(max = MAX_LENGTH, message = MAX_LENGTH_VALIDATION_MSG)
    @FormParam(PARAM_LOCATION_NAME)
    @DefaultValue("")
    private String locationName;

    @Length(max = MAX_LENGTH, message = MAX_LENGTH_VALIDATION_MSG)
    @FormParam(PARAM_LOCATION_ADDRESS)
    @DefaultValue("")
    private String locationAddress;

    @Length(max = MAX_LENGTH, message = MAX_LENGTH_VALIDATION_MSG)
    @FormParam(PARAM_PERSON_NAME)
    @DefaultValue("")
    private String personName;

    @Length(max = MAX_LENGTH, message = MAX_LENGTH_VALIDATION_MSG)
    @FormParam(PARAM_PERSON_ADDRESS)
    @DefaultValue("")
    private String personAddress;

    @NotEmpty(message = "enter the details of the incident")
    @NotBlank(message = "enter the details of the incident")
    @Length(max = COMMENT_FIELD_MAX_LENGTH, message = MAX_LENGTH_VALIDATION_MSG)
    @FormParam(PARAM_COMMENTS)
    private String comments;

    public String getVehicleReg() {
        return vehicleReg;
    }

    public String getLocationName() {
        return locationName;
    }

    public String getLocationAddress() {
        return locationAddress;
    }

    public String getPersonName() {
        return personName;
    }

    public String getPersonAddress() {
        return personAddress;
    }

    public String getComments() {
        return comments;
    }
}
