package uk.gov.dvsa.moti.web.healthCheck;

import com.amazonaws.AmazonClientException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import uk.gov.dvsa.moti.persistence.S3Storage;

public class S3StorageHealthChecker extends S3Storage {

    private static Logger logger = LoggerFactory.getLogger(S3StorageHealthChecker.class);

    public S3StorageHealthChecker(String bucket, String prefix) {
        super(bucket, prefix);
    }

    public boolean ping(){
        try {
            return client.doesBucketExist(bucket);
        } catch (AmazonClientException e){
            logger.error("Error while trying to connect to S3");
            return false;
        }
    }
}
