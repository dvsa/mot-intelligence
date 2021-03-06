package uk.gov.dvsa.moti.processing.executor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.xml.bind.DatatypeConverter;

/**
 * Helper class creating sha256 hash
 */
public class ShaHasher {
    private static Logger logger = LoggerFactory.getLogger(ShaHasher.class);

    /**
     * Perform sha256 hashing
     * @param stringToBeHashed
     * @return
     */
    public String sha256(String stringToBeHashed) {
        String hash = "";
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            hash = DatatypeConverter.printHexBinary(digest.digest(stringToBeHashed.getBytes("UTF-8")));
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            logger.error("Error while creating SHA hash", e);
            throw new ProcessorException("Error while creating SHA hash", e);
        }

        return hash.toLowerCase();
    }
}
