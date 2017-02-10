package uk.gov.dvsa.moti.web.config;

import io.dropwizard.Configuration;

public class PrivateS3BucketConfiguration extends Configuration {
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
