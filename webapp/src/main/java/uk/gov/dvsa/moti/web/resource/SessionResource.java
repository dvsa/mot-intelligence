package uk.gov.dvsa.moti.web.resource;

import uk.gov.dvsa.moti.web.model.FraudModel;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.servlet.http.HttpSession;

@Singleton
public class SessionResource implements SessionResourceInterface {

    private String sessionValueId = "session_value";

    @Inject
    HttpSession session;

    @Override
    public FraudModel get() {

        return (FraudModel) session.getAttribute(sessionValueId);
    }

    @Override
    public void save(FraudModel object) {

        if(session.getAttribute(sessionValueId) != null) {
            remove();
        }
        session.setAttribute(sessionValueId, object);
    }

    @Override
    public void remove() {
        session.removeAttribute(sessionValueId);
    }
}

