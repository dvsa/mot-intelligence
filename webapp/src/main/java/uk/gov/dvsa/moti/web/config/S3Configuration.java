package uk.gov.dvsa.moti.web.config;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.dropwizard.Configuration;

public class S3Configuration extends Configuration {
    @JsonProperty("privateBucket")
    private PrivateS3BucketConfiguration privateBucketConfiguration;

    public PrivateS3BucketConfiguration getPrivateBucketConfiguration() {
        return privateBucketConfiguration;
    }

    @JsonProperty("publicBucket")
    private PublicS3BucketConfiguration publicBucketConfiguration;

    public PublicS3BucketConfiguration getPublicBucketConfiguration() {
        return publicBucketConfiguration;
    }
}
