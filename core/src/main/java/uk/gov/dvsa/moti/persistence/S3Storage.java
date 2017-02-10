package uk.gov.dvsa.moti.persistence;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AnonymousAWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.DeleteObjectsRequest;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectSummary;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public final class S3Storage implements FileStorage {
    private AmazonS3Client client;
    private String storePrefix;
    private String bucket;

    public S3Storage(String bucket, String prefix) {
        this(bucket, prefix, null, null);
    }

    public S3Storage(String bucket, String prefix, String accessKey, String secretKey) {
        this.bucket = bucket;
        this.storePrefix = prefix;

        AWSCredentials clientCredentials = accessKey != null && !accessKey.equals("")
                ? new BasicAWSCredentials(accessKey, secretKey)
                : new AnonymousAWSCredentials();

        client = new AmazonS3Client(clientCredentials);
    }

    public void store(String key, String fileContents) {
        store(key, fileContents.getBytes(StandardCharsets.UTF_8));
    }

    public void store(String key, byte[] fileContents) {
        InputStream stream = new ByteArrayInputStream(fileContents);

        key = withPrefix(key);

        // we actually do not require any metdata to be assigned to the file
        ObjectMetadata emptyMetadata = new ObjectMetadata();

        client.putObject(bucket, key, stream, emptyMetadata);
    }

    public void delete(String key) {
        client.deleteObject(bucket, withPrefix(key));
    }

    public void delete(List<String> keys) {
        List<DeleteObjectsRequest.KeyVersion> keysVersions = new ArrayList<>(keys.size());

        for (String key : keys) {
            keysVersions.add(new DeleteObjectsRequest.KeyVersion(key));
        }

        DeleteObjectsRequest request = new DeleteObjectsRequest(bucket);

        request.setKeys(keysVersions);
        client.deleteObjects(request);
    }

    @Override
    public List<String> getMultiple(String keyPrefix, int limit) {
        List<String> keys = list(withPrefix(keyPrefix));
        limit = Math.min(limit, keys.size());

        keys = keys.subList(0, limit);

        List<String> files = new ArrayList<>(keys.size());

        for (String key : keys) {
            String file = get(key);
            files.add(file);
        }

        return files;
    }

    public String get(String key) {
        S3Object object = client.getObject(bucket, withPrefix(key));

        InputStream stream = object.getObjectContent();
        ByteArrayOutputStream result = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int length;
        try {
            while ((length = stream.read(buffer)) != -1) {
                result.write(buffer, 0, length);
            }

            return result.toString("UTF-8");
        } catch (UnsupportedEncodingException ex) {
            throw new FileStorageException("Not able to encode incoming document with UTF-8.", ex);
        } catch (IOException ex) {
            throw new FileStorageException("Not able to encode incoming document with UTF-8.", ex);
        }
    }

    private List<String> list(String keyPrefix) {
        ObjectListing listing = client.listObjects(bucket, keyPrefix);
        List<S3ObjectSummary> summaries = listing.getObjectSummaries();

        int prefixLength = keyPrefix.length();

        List<String> keys = summaries
                .stream()
                .map(summary -> summary.getKey())
                .map(fullKey -> fullKey.substring(prefixLength))
                .collect(Collectors.toList());

        return keys;
    }

    private String withPrefix(String key) {
        return storePrefix + key;
    }
}
