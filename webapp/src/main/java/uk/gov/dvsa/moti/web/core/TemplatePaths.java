package uk.gov.dvsa.moti.web.core;

public class TemplatePaths {
    public static String ROOT = "/uk/gov/dvsa/moti/web/views/";
    public static String PACKAGE_ERROR = "core/error/";
    public static String EXTENSION = ".hbs";

    public static String buildErrorTemplatePath(String errorTemplate) {
        return ROOT + PACKAGE_ERROR + errorTemplate;
    }
}
