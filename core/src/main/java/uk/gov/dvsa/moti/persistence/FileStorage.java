package uk.gov.dvsa.moti.persistence;

import java.io.File;
import java.util.List;

public interface FileStorage {
    List<String> getMultiple(String keyPrefix, int limit);
    String get(String keyPrefix);
    void store(String key, String fileContents);
    void store(String key, byte[] fileContents);
    void delete(String key);
    void delete(List<String> keys);
}
