package uk.gov.dvsa.moti;

import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import uk.gov.dvsa.moti.fraudserializer.xml.Fraud;

import java.util.UUID;

public class FraudHelper {

    public static Fraud createFraud() {
        return new Fraud()
                .setId(UUID.randomUUID().toString())
                .setCreateDate(LocalDate.parse("2016-10-10"))
                .setCreateTime(LocalTime.parse("10:00:00"))
                .setComments("Something bad happened")
                .setLocationAddress("Bristol")
                .setLocationName("John's Garage")
                .setPersonAddress("My Home")
                .setPersonName("John Smith")
                .setVehicleReg("BD51SMR");
    }
}
