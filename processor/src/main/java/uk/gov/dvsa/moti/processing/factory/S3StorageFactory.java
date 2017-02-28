package uk.gov.dvsa.moti.processing.factory;

import com.google.inject.Inject;
import com.google.inject.name.Named;

import uk.gov.dvsa.moti.common.configuration.model.S3BucketConfiguration;
import uk.gov.dvsa.moti.persistence.FileStorage;
import uk.gov.dvsa.moti.persistence.S3Storage;
import uk.gov.dvsa.moti.processing.configuration.model.ConfigurationName;
import uk.gov.dvsa.moti.processing.configuration.model.S3Configuration;

public class S3StorageFactory {
    private S3Configuration s3Configuration;

    @Inject
    public S3StorageFactory(@Named(ConfigurationName.S3) S3Configuration s3Configuration) {
        this.s3Configuration = s3Configuration;
    }

    public FileStorage getSourceBucketStorage() {
        return getS3BucketStorage(s3Configuration.getSourceBucket());
    }

    public FileStorage getDestinationBucketStorage() {
        return getS3BucketStorage(s3Configuration.getDestinationBucket());
    }

    private S3Storage getS3BucketStorage(S3BucketConfiguration bucketConfiguration) {
        if (bucketConfiguration.getSecretKey() == null || bucketConfiguration.getAccessKey() == null) {
            return new S3Storage(
                    bucketConfiguration.getBucket(),
                    bucketConfiguration.getRootFolder()
            );
        } else {
            return new S3Storage(
                    bucketConfiguration.getBucket(),
                    bucketConfiguration.getRootFolder(),
                    bucketConfiguration.getAccessKey(),
                    bucketConfiguration.getSecretKey()
            );
        }
    }
}
