package uk.gov.dvsa.moti.processing.executor;

import java.io.ByteArrayOutputStream;

public interface DocumentCompressorInterface {
    public ByteArrayOutputStream compressFiles(String documentAsString, String documentFilename, String manifestContent);
}
