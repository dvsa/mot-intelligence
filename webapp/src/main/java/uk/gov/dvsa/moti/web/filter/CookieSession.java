package uk.gov.dvsa.moti.web.filter;

import java.io.Serializable;
import java.util.Map;

public class CookieSession implements Serializable {

    private Map<String, Object> attributes;
    public Map<String, Object> getAttributes() {
        return attributes;
    }
    public CookieSession setAttributes(Map<String, Object> attributes) {
        this.attributes = attributes;
        return this;
    }
}
