package uk.gov.dvsa.moti.common.configuration.model;

public class S3BucketConfiguration {

    /**
     * S3 Bucket root folder path
     */
    private String rootFolder;

    public String getRootFolder() {
        return rootFolder;
    }

    /**
     * S3 Bucket name
     */
    private String bucket;

    public String getBucket() {
        return bucket;
    }
}
