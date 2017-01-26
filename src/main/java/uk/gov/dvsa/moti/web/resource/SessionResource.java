package uk.gov.dvsa.moti.web.resource;

import uk.gov.dvsa.moti.web.model.FraudInterface;

import io.dropwizard.jersey.sessions.Session;

import javax.inject.Singleton;
import javax.servlet.http.HttpSession;

@Singleton
public class SessionResource implements SessionResourceInterface {

    private final HttpSession session;

    public SessionResource(@Session HttpSession session) {
        this.session = session;
    }

    @Override
    public FraudInterface get(String formUuid) {
        return (FraudInterface) session.getAttribute(formUuid);
    }

    @Override
    public void save(String formUuid, FraudInterface object) {
        if(session.getAttribute(formUuid) != null) {
            remove(formUuid);
        }
        session.setAttribute(formUuid, object);
    }

    @Override
    public void remove(String formUuid) {
        session.removeAttribute(formUuid);
    }
}

