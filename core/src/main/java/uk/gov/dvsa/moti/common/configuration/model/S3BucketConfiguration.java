package uk.gov.dvsa.moti.common.configuration.model;

public class S3BucketConfiguration {
    private String accessKey;

    public String getAccessKey() {
        return accessKey;
    }

    private String secretKey;

    public String getSecretKey() {
        return secretKey;
    }

    private String rootFolder;

    public String getRootFolder() {
        return rootFolder;
    }

    private String bucket;

    public String getBucket() {
        return bucket;
    }
}
