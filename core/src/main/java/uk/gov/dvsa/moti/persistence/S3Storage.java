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
    private String storeRoot;
    private String bucket;

    public S3Storage(String bucket, String prefix) {
        this(bucket, prefix, null, null);
    }

    public S3Storage(String bucket, String prefix, String accesspath, String secretpath) {
        this.bucket = bucket;
        this.storeRoot = prefix;

        AWSCredentials clientCredentials = accesspath != null && !accesspath.equals("")
                ? new BasicAWSCredentials(accesspath, secretpath)
                : new AnonymousAWSCredentials();

        client = new AmazonS3Client(clientCredentials);
    }

    @Override
    public void store(File file) {
        store(file.getPath(), file.getContent());
    }

    public void store(String path, String fileContent) {

        store(path, fileContent.getBytes(StandardCharsets.UTF_8));
    }

    public void store(String path, byte[] fileContent) {
        InputStream stream = new ByteArrayInputStream(fileContent);

        path = withRootPrefix(path);

        // we actually do not require any metdata to be assigned to the file
        ObjectMetadata emptyMetadata = new ObjectMetadata();

        client.putObject(bucket, path, stream, emptyMetadata);
    }

    public void delete(String path) {
        client.deleteObject(bucket, withRootPrefix(path));
    }

    public void delete(List<String> paths) {
        List<DeleteObjectsRequest.KeyVersion> pathsVersions = new ArrayList<>(paths.size());

        for (String path : paths) {
            pathsVersions.add(new DeleteObjectsRequest.KeyVersion(withRootPrefix(path)));
        }

        DeleteObjectsRequest request = new DeleteObjectsRequest(bucket);

        request.setKeys(pathsVersions);
        client.deleteObjects(request);
    }

    @Override
    public List<File> getMultiple(String pathPrefix, int limit) {
        List<String> paths = list(pathPrefix);
        limit = Math.min(limit, paths.size());

        paths = paths.subList(0, limit);

        List<File> files = new ArrayList<>(paths.size());

        for (String path : paths) {
            File file = get(path);
            files.add(file);
        }

        return files;
    }

    public File get(String path) {
        S3Object object = client.getObject(bucket, withRootPrefix(path));

        InputStream stream = object.getObjectContent();
        ByteArrayOutputStream result = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int length;
        try {
            while ((length = stream.read(buffer)) != -1) {
                result.write(buffer, 0, length);
            }

            return new File(path, result.toByteArray());
        } catch (UnsupportedEncodingException ex) {
            throw new FileStorageException("Not able to encode incoming document with UTF-8.", ex);
        } catch (IOException ex) {
            throw new FileStorageException("Not able to encode incoming document with UTF-8.", ex);
        }
    }

    private List<String> list(String pathPrefix) {
        ObjectListing listing = client.listObjects(bucket, withRootPrefix(pathPrefix));
        List<S3ObjectSummary> summaries = listing.getObjectSummaries();

        int rootLength = this.storeRoot.length();

        List<String> paths = summaries
                .stream()
                .map(summary -> summary.getKey())
                .map(fullpath -> fullpath.substring(rootLength))
                .collect(Collectors.toList());

        return paths;
    }

    private String withRootPrefix(String path) {
        return storeRoot + path;
    }
}
