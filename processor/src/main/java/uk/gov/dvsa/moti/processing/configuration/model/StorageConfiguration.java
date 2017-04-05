package uk.gov.dvsa.moti.processing.configuration.model;

/**
 * File storage configuration
 */
public class StorageConfiguration {
    private String downloadedFilesPrefix;
    private Integer maxDownloadedFilesLimit;
    private String destinationFilePath;

    public String getDownloadedFilesPrefix() {
        return downloadedFilesPrefix;
    }

    public Integer getMaxDownloadedFilesLimit() {
        return maxDownloadedFilesLimit;
    }

    public String getDestinationFilePath() {
        return destinationFilePath;
    }

    public void setDestinationFilePath(String destinationFilePath) {
        this.destinationFilePath = destinationFilePath;
    }
}
