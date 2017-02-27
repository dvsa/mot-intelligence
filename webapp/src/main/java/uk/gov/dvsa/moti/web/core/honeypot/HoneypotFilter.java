package uk.gov.dvsa.moti.web.core.honeypot;

import uk.gov.dvsa.moti.web.filter.FilterResponseWrapper;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.HttpMethod;
import java.io.IOException;

public class HoneypotFilter implements Filter {

    public static final String FIELD_NAME = "whenisayhoneyyousaypot";

    private FilterConfig filterConfig;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
            ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;

        if(httpServletRequest.getMethod().equals(HttpMethod.POST)) {
            checkHoneypot(httpServletRequest);
        }

        FilterResponseWrapper responseWrapper = new FilterResponseWrapper((HttpServletResponse) response);
        chain.doFilter(request, responseWrapper);

        responseWrapper.flushBuffer();
    }

    @Override
    public void destroy() {
        filterConfig = null;
    }

    private void checkHoneypot(ServletRequest request) throws ServletException {
        String honeypot = request.getParameter(FIELD_NAME);
        if(honeypot != null && !honeypot.isEmpty()) {
            throw new ServletException("Honeypot in request");
        }
    }
}
