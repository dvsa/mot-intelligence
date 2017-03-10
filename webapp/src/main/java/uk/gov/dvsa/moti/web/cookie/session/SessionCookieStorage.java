package uk.gov.dvsa.moti.web.cookie.session;

import uk.gov.dvsa.moti.web.cookie.Cookies;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Base64;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SessionCookieStorage {

    private static String COOKIE_NAME = "session";

    public void getSessionFromCookie(HttpServletRequest httpServletRequest) throws IOException, ClassNotFoundException {
        Cookie sessionCookie = Cookies.getCookie(httpServletRequest, COOKIE_NAME);

        if (null != sessionCookie) {
            HttpSession session = httpServletRequest.getSession();
            session.invalidate();
            session = httpServletRequest.getSession();
            CookieSession cookieSession = (CookieSession) fromString(sessionCookie.getValue());
            cookieSession.getAttributes().forEach(session::setAttribute);
        }
    }
    public void storeSessionInCookie(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException {

        HttpSession session = httpServletRequest.getSession(true);
        Enumeration<String> attributeNames = session.getAttributeNames();

        if (attributeNames != null) {
            Map<String, Object> attributes =
                    Collections.list(attributeNames).stream().collect(Collectors.toMap(String::toString, session::getAttribute));

            CookieSession cookieSession = new CookieSession();
            cookieSession.setAttributes(attributes);
            Cookie newSessionCookie = Cookies.createCookie(COOKIE_NAME, toString(cookieSession), "/", 1200);
            httpServletResponse.addCookie(newSessionCookie);
        }
    }

    private String toString(Serializable object) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream.writeObject(object);
        objectOutputStream.close();
        return Base64.getEncoder().encodeToString(byteArrayOutputStream.toByteArray());
    }

    private Object fromString(String string) throws IOException, ClassNotFoundException {
        byte[] data = Base64.getDecoder().decode(string);
        ObjectInputStream objectInputStream = new ObjectInputStream(new ByteArrayInputStream(data));
        Object object = objectInputStream.readObject();
        objectInputStream.close();
        return object;
    }
}
