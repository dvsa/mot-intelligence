package uk.gov.dvsa.moti.fraudserializer.xml;

import org.joda.time.LocalDate;
import org.joda.time.LocalTime;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Fraud element (single row in fraud report)
 */
@XmlRootElement(name="row")
public class Fraud implements Serializable {

    private String vehicleReg;

    private String locationName;

    private String locationAddress;

    private String personName;

    private String personAddress;

    private String comments;

    private String id;

    private LocalDate createDate;

    private LocalTime createTime;

    @XmlAttribute(name="TargetVehicleRegistrationMark")
    public String getVehicleReg() {
        return vehicleReg;
    }

    public Fraud setVehicleReg(String vehicleReg) {
        this.vehicleReg = vehicleReg;
        return this;
    }

    @XmlAttribute(name="TargetCompanyName")
    public String getLocationName() {
        return locationName;
    }

    public Fraud setLocationName(String locationName) {
        this.locationName = locationName;
        return this;
    }

    @XmlAttribute(name="TargetCompanyAddress")
    public String getLocationAddress() {
        return locationAddress;
    }

    public Fraud setLocationAddress(String locationAddress) {
        this.locationAddress = locationAddress;
        return this;
    }

    @XmlAttribute(name="TargetPersonName")
    public String getPersonName() {
        return personName;
    }

    public Fraud setPersonName(String personName) {
        this.personName = personName;
        return this;

    }

    @XmlAttribute(name="TargetPersonAddress")
    public String getPersonAddress() {
        return personAddress;
    }

    public Fraud setPersonAddress(String personAddress) {
        this.personAddress = personAddress;
        return this;
    }

    @XmlAttribute(name="Description")
    public String getComments() {
        return comments;
    }

    public Fraud setComments(String comments) {
        this.comments = comments;
        return this;
    }

    @XmlAttribute(name="Id")
    public String getId() {
        return id;
    }

    public Fraud setId(String id) {
        this.id = id;
        return this;
    }

    @XmlAttribute(name="CreationDate")
    public LocalDate getCreateDate() {
        return createDate;
    }

    public Fraud setCreateDate(LocalDate createDate) {
        this.createDate = createDate;
        return this;
    }

    @XmlAttribute(name="CreationTime")
    public LocalTime getCreateTime() {
        return createTime;
    }

    public Fraud setCreateTime(LocalTime createTime) {
        this.createTime = createTime;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Fraud)) return false;

        Fraud fraud = (Fraud) o;

        if (vehicleReg != null ? !vehicleReg.equals(fraud.vehicleReg) : fraud.vehicleReg != null) return false;
        if (locationName != null ? !locationName.equals(fraud.locationName) : fraud.locationName != null) return false;
        if (locationAddress != null ? !locationAddress.equals(fraud.locationAddress) : fraud.locationAddress != null)
            return false;
        if (personName != null ? !personName.equals(fraud.personName) : fraud.personName != null) return false;
        if (personAddress != null ? !personAddress.equals(fraud.personAddress) : fraud.personAddress != null)
            return false;
        if (comments != null ? !comments.equals(fraud.comments) : fraud.comments != null) return false;
        if (id != null ? !id.equals(fraud.id) : fraud.id != null) return false;
        if (createDate != null ? !createDate.equals(fraud.createDate) : fraud.createDate != null) return false;
        return createTime != null ? createTime.equals(fraud.createTime) : fraud.createTime == null;

    }

    @Override
    public int hashCode() {
        int result = vehicleReg != null ? vehicleReg.hashCode() : 0;
        result = 31 * result + (locationName != null ? locationName.hashCode() : 0);
        result = 31 * result + (locationAddress != null ? locationAddress.hashCode() : 0);
        result = 31 * result + (personName != null ? personName.hashCode() : 0);
        result = 31 * result + (personAddress != null ? personAddress.hashCode() : 0);
        result = 31 * result + (comments != null ? comments.hashCode() : 0);
        result = 31 * result + (id != null ? id.hashCode() : 0);
        result = 31 * result + (createDate != null ? createDate.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        return result;
    }
}
