package uk.gov.dvsa.moti.web.fraudSender;


import uk.gov.dvsa.moti.enums.FileExtension;
import uk.gov.dvsa.moti.fraudserializer.FraudSerializer;
import uk.gov.dvsa.moti.fraudserializer.xml.Fraud;
import uk.gov.dvsa.moti.persistence.FileStorage;

import javax.inject.Inject;

/**
 * Sends xml Frauds to S3 Bucket
 */
public class XmlFraudSender implements FraudSender {

    private FraudSerializer fraudSerializer;
    private FileStorage fileStorage;

    @Inject
    public XmlFraudSender(FileStorage fileStorage, FraudSerializer fraudSerializer) {
        this.fileStorage = fileStorage;
        this.fraudSerializer = fraudSerializer;
    }

    /**
     * Send fraud report
     * @param fraudModel
     */
    public void send(Fraud fraudModel) {
        String serialized = fraudSerializer.serialize(fraudModel);
        fileStorage.store(getFileName(fraudModel.getId()), serialized);
    }

    /**
     * Build file name for stored fraud report
     * @param fraudModelId
     * @return
     */
    private String getFileName(String fraudModelId) {
        return fraudModelId + FileExtension.XML.getExtension();
    }
}
