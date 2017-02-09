package uk.gov.dvsa.moti.processor.executor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.xml.bind.DatatypeConverter;

public class ShaHasher {
    private static Logger logger = LoggerFactory.getLogger(ShaHasher.class);

    public String sha256(String stringToBeHashed) {
        String hash = "";
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            hash = DatatypeConverter.printHexBinary(digest.digest(stringToBeHashed.getBytes("UTF-8")));
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            logger.error("Error while creating SHA hash", e);
        }

        return hash.toLowerCase();
    }
}
