package uk.gov.dvsa.moti.web.filter;

import org.eclipse.jetty.server.Request;

import uk.gov.dvsa.moti.enums.HttpResponseCode;
import uk.gov.dvsa.moti.web.cookie.Cookies;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

/**
 * Validates CSRF token
 */
public class VerifyCsrfTokenFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        Request request = (Request) servletRequest;

        if (request.getMethod().equals("POST")) {
            Cookie cookie = Cookies.getCookie(request, CsrfTokenFilter.COOKIE_NAME);
            String receivedCsrfToken = request.getParameter("csrfToken");

            if (cookie == null || !cookie.getValue().equals(receivedCsrfToken)) {
                HttpServletResponse httpResponse = (HttpServletResponse) response;
                httpResponse.sendError(HttpResponseCode.SC_SC_REQUEST_TIMEOUT.getCode());
                return;
            }
        }

    FilterResponseWrapper responseWrapper = new FilterResponseWrapper((HttpServletResponse) response);
    chain.doFilter(request,responseWrapper);

    responseWrapper.flushBuffer();
}

    @Override
    public void destroy() {

    }
}
