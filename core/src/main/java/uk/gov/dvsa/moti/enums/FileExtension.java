package uk.gov.dvsa.moti.enums;

/**
 * Helper class for file extensions
 */
public enum FileExtension {
    XML(".xml");

    private String extension;

    private FileExtension(String extension) {
        this.extension = extension;
    }

    public String getExtension() {
        return extension;
    }
}
