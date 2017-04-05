package uk.gov.dvsa.moti.web.routing;

import javax.ws.rs.core.UriBuilder;
import java.net.URI;

/**
 * Helper class with FraudResource routes
 */
public class FraudRoutes {
    public static final String ROOT = "/fraud";
    public static final String SUMMARY = "/summary";
    public static final String REPORT = "/report";
    public static final String COOKIE_POLICY = "/cookie-policy";

    public static String getFormPath() {
        return getFormUri().toString();
    }

    public static URI getFormUri() {
        return getUri(ROOT);
    }

    public static URI getSummaryUri() {
        return getUri(ROOT + SUMMARY);
    }

    public static URI getReportUri() {
        return getUri(ROOT + REPORT);
    }

    public static String getCookiePolicyPath() {
        return getUri(ROOT + COOKIE_POLICY).toString();
    }

    private static URI getUri(String path) {
        return UriBuilder.fromUri(path).build();
    }
}
