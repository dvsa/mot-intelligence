package uk.gov.dvsa.moti.persistence;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

/**
 * File
 */
public class File {
    private String path;

    public String getPath() {
        return path;
    }

    private byte[] content;

    public byte[] getContent() {
        return content;
    }

    public File(String path, byte[] content) {
        this.path = path;
        this.content = content;
    }

    public File(String path, String content) {
        this.path = path;
        this.content = content.getBytes(StandardCharsets.UTF_8);
    }

    /**
     * Read file contents as string
     * @return
     */
    public String getContentAsString() {
        ByteArrayOutputStream result = new ByteArrayOutputStream();

        InputStream stream = new ByteArrayInputStream(content);

        byte[] buffer = new byte[1024];
        int length;
        try {
            while ((length = stream.read(buffer)) != -1) {
                result.write(buffer, 0, length);
            }

            return result.toString();
        } catch (IOException ex) {
            throw new FileStorageException("Not able to encode incoming document.", ex);
        }
    }

}
