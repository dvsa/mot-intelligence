package uk.gov.dvsa.moti.web.model;


import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import javax.ws.rs.FormParam;

public class FraudModel {
    public static final String PARAM_VEHICLE_REG = "vehicleReg";
    public static final String PARAM_VEHICLE_MAKE = "vehicleMake";
    public static final String PARAM_VEHICLE_MODEL = "vehicleModel";
    public static final String PARAM_LOCATION_NAME = "locationName";
    public static final String PARAM_LOCATION_ADDRESS = "locationAddress";
    public static final String PARAM_PERSON_NAME = "personName";
    public static final String PARAM_PERSON_ADDRESS = "personAddress";
    public static final String PARAM_COMMENTS = "comments";

    @FormParam(PARAM_VEHICLE_REG)
    private String vehicleReg;

    @FormParam(PARAM_VEHICLE_MAKE)
    private String vehicleMake;

    @FormParam(PARAM_VEHICLE_MODEL)
    private String vehicleModel;

    @FormParam(PARAM_LOCATION_NAME)
    private String locationName;

    @FormParam(PARAM_LOCATION_ADDRESS)
    private String locationAddress;

    @FormParam(PARAM_PERSON_NAME)
    private String personName;

    @FormParam(PARAM_PERSON_ADDRESS)
    private String personAddress;

    @NotEmpty(message = "enter the details of the incident")
    @NotBlank(message = "enter the details of the incident")
    @FormParam(PARAM_COMMENTS)
    private String comments;

    public String getVehicleReg() {
        return vehicleReg;
    }

    public FraudModel setVehicleReg(String vehicleReg) {
        this.vehicleReg = vehicleReg;
        return this;
    }

    public String getVehicleMake() {
        return vehicleMake;
    }

    public FraudModel setVehicleMake(String vehicleMake) {
        this.vehicleMake = vehicleMake;

        return this;
    }

    public String getVehicleModel() {
        return vehicleModel;
    }

    public FraudModel setVehicleModel(String vehicleModel) {
        this.vehicleModel = vehicleModel;

        return this;
    }

    public String getLocationName() {
        return locationName;
    }

    public FraudModel setLocationName(String locationName) {
        this.locationName = locationName;

        return this;
    }

    public String getLocationAddress() {
        return locationAddress;
    }

    public FraudModel setLocationAddress(String locationAddress) {
        this.locationAddress = locationAddress;

        return this;
    }

    public String getPersonName() {
        return personName;
    }

    public FraudModel setPersonName(String personName) {
        this.personName = personName;

        return this;
    }

    public String getPersonAddress() {
        return personAddress;
    }

    public FraudModel setPersonAddress(String personAddress) {
        this.personAddress = personAddress;

        return this;
    }

    public String getComments() {
        return comments;
    }

    public FraudModel setComments(String comments) {
        this.comments = comments;

        return this;
    }
}
