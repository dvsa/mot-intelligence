package uk.gov.dvsa.moti.web.filter;

import uk.gov.dvsa.moti.web.cookie.Cookies;
import uk.gov.dvsa.moti.web.model.CsrfToken;

import java.io.IOException;
import java.util.UUID;

import javax.annotation.Priority;
import javax.inject.Inject;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Context;
import javax.ws.rs.ext.Provider;

@Provider
@Priority(Priorities.AUTHENTICATION)
public class CsrfTokenFilter implements ContainerRequestFilter {
    @Inject
    private javax.inject.Provider<CsrfToken> csrfToken;
    @Context
    private HttpServletResponse webResponse;

    public static String COOKIE_NAME = "csrf_token";
    private String cookiePath = "/";
    private int cookieMaxAge = 1200;

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        csrfToken.get().setCsrfToken(UUID.randomUUID().toString());
        Cookie cookie = Cookies.createCookie(COOKIE_NAME, csrfToken.get().getCsrfToken(), cookiePath, cookieMaxAge);
        webResponse.addCookie(cookie);
    }
}
