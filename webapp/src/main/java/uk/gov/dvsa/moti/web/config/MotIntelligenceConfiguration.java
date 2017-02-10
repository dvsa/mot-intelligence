package uk.gov.dvsa.moti.web.config;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.dropwizard.Configuration;

import uk.gov.dvsa.moti.web.config.S3Configuration;

public class MotIntelligenceConfiguration extends Configuration {
    @JsonProperty("s3")
    private S3Configuration s3configuration;
    public S3Configuration getS3Configuration() { return s3configuration; }
}