package uk.gov.dvsa.moti.web.config;

import io.dropwizard.Configuration;

public class PublicS3BucketConfiguration extends Configuration {
    private String accessKey;

    public String getAccessKey() {
        return accessKey;
    }

    private String secretKey;

    public String getSecretKey() {
        return secretKey;
    }

    private String filePath;

    public String getFilePath() {
        return filePath;
    }

    private String bucket;

    public String getBucket() {
        return bucket;
    }
}
