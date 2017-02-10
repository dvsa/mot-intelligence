package uk.gov.dvsa.moti.persistence;

public class S3StorageCredentials {
    String accessKey;
    public String getAccessKey() {
        return accessKey;
    }

    String secretKey;
    public String getSecretKey() {
        return secretKey;
    }

    public S3StorageCredentials(String accessKey, String secretKey) {
        this.accessKey = accessKey;
        this.secretKey = secretKey;
    }
}
