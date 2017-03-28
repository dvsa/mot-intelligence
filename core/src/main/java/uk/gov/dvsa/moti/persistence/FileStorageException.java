package uk.gov.dvsa.moti.persistence;

/**
 * Exception thrown in File and S3Storage
 */
public class FileStorageException extends RuntimeException {
    public FileStorageException() {
        super();
    }

    public FileStorageException(String message) {
        super(message);
    }

    public FileStorageException(String message, Throwable cause) {
        super(message, cause);
    }
}
