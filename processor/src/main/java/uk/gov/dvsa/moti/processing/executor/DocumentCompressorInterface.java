package uk.gov.dvsa.moti.processing.executor;

import java.io.ByteArrayOutputStream;

public interface DocumentCompressorInterface {
    byte[] compressFiles(byte[] documentAsString, String documentFilename, byte[] manifestContent);
}
