package uk.gov.dvsa.moti.processor.configuration.model;

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
}
