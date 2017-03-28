package uk.gov.dvsa.moti.processing.configuration.model;

import uk.gov.dvsa.moti.common.configuration.model.S3BucketConfiguration;

/**
 * S3 access configuration
 */
public class S3Configuration {
    private S3BucketConfiguration sourceBucket;

    public S3BucketConfiguration getSourceBucket() {
        return sourceBucket;
    }

    private S3BucketConfiguration destinationBucket;

    public S3BucketConfiguration getDestinationBucket() {
        return destinationBucket;
    }
}
