package uk.gov.dvsa.moti.web.fraudSender.mapper;

import org.joda.time.LocalDate;
import org.joda.time.LocalTime;

import uk.gov.dvsa.moti.fraudserializer.xml.Fraud;
import uk.gov.dvsa.moti.web.model.FraudModel;

public class XmlFraudMapper {
    /**
     * Build xml Fraud from FraudModel
     * @param fraudModel
     * @return
     */
    private static Fraud getFraud(FraudModel fraudModel) {
        return new Fraud()
                .setComments(fraudModel.getComments())
                .setLocationAddress(fraudModel.getLocationAddress())
                .setPersonAddress(fraudModel.getPersonAddress())
                .setPersonName(fraudModel.getPersonName())
                .setLocationName(fraudModel.getLocationName())
                .setVehicleReg(fraudModel.getVehicleReg());
    }

    /**
     * Build xml Fraud from FraudModel and UUID
     * @param model
     * @param formUuid
     * @return
     */
    public static Fraud getXmlFraudModel(FraudModel model, String formUuid) {
        Fraud xmlFraudModel = getFraud(model);
        xmlFraudModel
                .setCreateTime(new LocalTime())
                .setCreateDate(new LocalDate())
                .setId(formUuid);
        return xmlFraudModel;
    }
}
