package uk.gov.dvsa.moti.web.healthCheck;

import com.codahale.metrics.Gauge;
import com.codahale.metrics.health.HealthCheck;

import uk.gov.dvsa.moti.common.configuration.model.S3BucketConfiguration;
import uk.gov.dvsa.moti.web.configuration.model.MotIntelligenceConfiguration;

/**
 * Healthcheck checking S3 connection
 */
public class MotiHealthCheck extends HealthCheck implements Gauge {
    private final S3StorageHealthChecker s3Storage;
    private float pingTime;

    public MotiHealthCheck(MotIntelligenceConfiguration motIntelligenceConfiguration) {
        S3BucketConfiguration privateS3BucketConfiguration = motIntelligenceConfiguration
                .getS3Configuration();

        s3Storage = new S3StorageHealthChecker(
                privateS3BucketConfiguration.getBucket(),
                privateS3BucketConfiguration.getRootFolder()
        );
    }

    @Override
    protected Result check() {
        long startTime = System.currentTimeMillis();

        boolean ping = s3Storage.ping();

        long stopTime = System.currentTimeMillis();
        pingTime = stopTime - startTime;

        return ping ? Result.healthy() : Result.unhealthy("Bucket does not exist, or app cannot connect to S3");
    }

    @Override
    public String getValue() {
        check();

        return String.valueOf(pingTime / 1000) + " s";
    }
}
