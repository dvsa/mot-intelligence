package uk.gov.dvsa.moti.enums;

import org.eclipse.jetty.server.Response;

public enum HttpResponseCode {
    SC_NOT_FOUND(Response.SC_NOT_FOUND),
    SC_REQUEST_TIMEOUT(Response.SC_REQUEST_TIMEOUT),
    SC_INTERNAL_SERVER_ERROR(Response.SC_INTERNAL_SERVER_ERROR),
    SC_SC_REQUEST_TIMEOUT(Response.SC_REQUEST_TIMEOUT);



    private int code;

    HttpResponseCode(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public String getText() {
        return String.valueOf(code);
    }
}
