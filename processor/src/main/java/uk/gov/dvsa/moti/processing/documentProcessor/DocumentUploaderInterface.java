package uk.gov.dvsa.moti.processing.documentProcessor;

import java.io.ByteArrayOutputStream;

public interface DocumentUploaderInterface {
    void uploadDocument(byte[] document);
}
