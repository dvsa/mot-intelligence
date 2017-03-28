package uk.gov.dvsa.moti.processing.executor;

public interface DocumentCompressorInterface {
    byte[] compressFiles(byte[] documentAsString, String documentFilename, byte[] manifestContent);
}
