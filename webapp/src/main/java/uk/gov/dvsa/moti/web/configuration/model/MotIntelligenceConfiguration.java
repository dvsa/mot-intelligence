package uk.gov.dvsa.moti.web.configuration.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.dropwizard.Configuration;

import uk.gov.dvsa.moti.common.configuration.model.S3BucketConfiguration;

/**
 * MOTI configuration
 */
public class MotIntelligenceConfiguration extends Configuration {
    @JsonProperty("s3")
    private S3BucketConfiguration s3configuration;
    public S3BucketConfiguration getS3Configuration() { return s3configuration; }
}