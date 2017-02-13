package uk.gov.dvsa.moti.processor.configuration.model;

import uk.gov.dvsa.moti.common.configuration.model.S3BucketConfiguration;


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
