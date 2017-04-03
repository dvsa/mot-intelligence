package uk.gov.dvsa.moti.web.fraudSender;

import org.glassfish.hk2.api.Factory;

import uk.gov.dvsa.moti.common.configuration.model.S3BucketConfiguration;
import uk.gov.dvsa.moti.fraudserializer.FraudSerializer;
import uk.gov.dvsa.moti.persistence.FileStorage;
import uk.gov.dvsa.moti.persistence.S3Storage;
import uk.gov.dvsa.moti.web.configuration.model.MotIntelligenceConfiguration;

import javax.inject.Inject;

public class FraudSenderFactory implements Factory<FraudSender> {

    private final FraudSender fraudSender;

    @Inject
    public FraudSenderFactory(MotIntelligenceConfiguration motIntelligenceConfiguration) {

        S3BucketConfiguration privateS3BucketConfiguration = motIntelligenceConfiguration
                .getS3Configuration();

        FileStorage fileStorage = new S3Storage(
                privateS3BucketConfiguration.getBucket(),
                privateS3BucketConfiguration.getRootFolder()
        );

        FraudSerializer fraudSerializer = new FraudSerializer();

        this.fraudSender = new XmlFraudSender(fileStorage, fraudSerializer);
    }

    @Override
    public FraudSender provide() {
        return fraudSender;
    }

    @Override
    public void dispose(FraudSender instance) {

    }
}
