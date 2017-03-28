package uk.gov.dvsa.moti.web.model;

import org.glassfish.jersey.process.internal.RequestScoped;

import javax.ws.rs.Path;

/**
 * Model for keeping CSRF Token value
 */
@RequestScoped
@Path("/*")
public class CsrfToken {
    private String csrfToken = "";

    public String getCsrfToken() {
        return csrfToken;
    }

    public void setCsrfToken(String csrfToken) {
        this.csrfToken = csrfToken;
    }
}
