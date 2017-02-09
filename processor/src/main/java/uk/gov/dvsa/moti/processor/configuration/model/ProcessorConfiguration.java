package uk.gov.dvsa.moti.processor.configuration.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProcessorConfiguration {
    @JsonProperty("s3")
    private S3Configuration s3configuration;
    @JsonProperty("storage")
    private StorageConfiguration storageConfiguration;
    @JsonProperty("tarFile")
    private TarFileConfiguration tarFileConfiguration;

    public S3Configuration getS3Configuration() {
        return s3configuration;
    }

    public StorageConfiguration getStorageConfiguration() {
        return storageConfiguration;
    }

    public TarFileConfiguration getTarFileConfiguration() {
        return tarFileConfiguration;
    }
}
