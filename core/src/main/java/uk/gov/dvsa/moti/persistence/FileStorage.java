package uk.gov.dvsa.moti.persistence;

import java.util.List;

public interface FileStorage {
    List<File> getMultiple(String keyPrefix, int limit);
    File get(String keyPrefix);
    void store(File file);
    void store(String key, String fileContent);
    void store(String key, byte[] fileContent);
    void delete(String key);
    void delete(List<String> keys);
}
