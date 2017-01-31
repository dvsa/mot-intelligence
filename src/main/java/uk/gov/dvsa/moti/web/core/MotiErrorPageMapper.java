package uk.gov.dvsa.moti.web.core;

import org.eclipse.jetty.server.handler.ErrorHandler.ErrorPageMapper;

import javax.servlet.http.HttpServletRequest;

public class MotiErrorPageMapper implements ErrorPageMapper {
    @Override
    public String getErrorPage(HttpServletRequest request) {
        return null;
    }
}
