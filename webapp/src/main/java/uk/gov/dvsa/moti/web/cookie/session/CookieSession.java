package uk.gov.dvsa.moti.web.cookie.session;

import java.io.Serializable;
import java.util.Map;

/**
 * Representation of single session
 */
public class CookieSession implements Serializable {
    private static final long serialVersionUID = 1L;

    private Map<String, Object> attributes;

    public Map<String, Object> getAttributes() {
        return attributes;
    }

    public CookieSession setAttributes(Map<String, Object> attributes) {
        this.attributes = attributes;
        return this;
    }
}
