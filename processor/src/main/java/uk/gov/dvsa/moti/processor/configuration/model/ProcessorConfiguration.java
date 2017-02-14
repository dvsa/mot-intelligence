package uk.gov.dvsa.moti.processor.configuration.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProcessorConfiguration {
    @JsonProperty("s3")
    private S3Configuration s3configuration;

    public S3Configuration getS3Configuration() {
        return s3configuration;
    }

    public String destinationFilePath;

    public String getDestinationFilePath() {
        return destinationFilePath;
    }
}
