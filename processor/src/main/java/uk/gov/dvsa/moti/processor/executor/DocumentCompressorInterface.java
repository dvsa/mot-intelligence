package uk.gov.dvsa.moti.processor.executor;

import java.io.ByteArrayOutputStream;

public interface DocumentCompressorInterface {
    public ByteArrayOutputStream compressFiles(String documentAsString, String documentFilename, String manifestContent);
}
