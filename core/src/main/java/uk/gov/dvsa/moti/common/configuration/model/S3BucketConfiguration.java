package uk.gov.dvsa.moti.common.configuration.model;

public class S3BucketConfiguration {

    private String rootFolder;
    private String bucket;

    public String getRootFolder() {
        return rootFolder;
    }

    public String getBucket() {
        return bucket;
    }
}
