package uk.gov.dvsa.moti.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SessionFilter implements Filter {
    private FilterConfig filterConfig;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
            ServletException {
        SessionCookieStorage sessionCookieStorage = new SessionCookieStorage();
        try {
            sessionCookieStorage.getSessionFromCookie((HttpServletRequest) request);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        FilterResponseWrapper responseWrapper = new FilterResponseWrapper((HttpServletResponse) response);
        chain.doFilter(request, responseWrapper);

        responseWrapper.flushBuffer();
    }

    @Override
    public void destroy() {
        filterConfig = null;
    }
}
