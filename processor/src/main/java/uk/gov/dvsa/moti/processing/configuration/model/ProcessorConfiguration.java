package uk.gov.dvsa.moti.processing.configuration.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Processor configuration, container for other configuration parts
 */
public class ProcessorConfiguration {
    @JsonProperty("s3")
    private S3Configuration s3configuration;
    @JsonProperty("storage")
    private StorageConfiguration storageConfiguration;
    @JsonProperty("tarFile")
    private TarFileConfiguration tarFileConfiguration;
    @JsonProperty("settings")
    private ProcessorSettingsConfiguration processorSettingsConfiguration;

    public S3Configuration getS3Configuration() {
        return s3configuration;
    }

    public StorageConfiguration getStorageConfiguration() {
        return storageConfiguration;
    }

    public TarFileConfiguration getTarFileConfiguration() {
        return tarFileConfiguration;
    }

    public ProcessorSettingsConfiguration getProcessorSettingsConfiguration() {
        return processorSettingsConfiguration;
    }
}
