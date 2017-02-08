package uk.gov.dvsa.moti.web.resource;

import uk.gov.dvsa.moti.web.model.FraudModel;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.servlet.http.HttpSession;

@Singleton
public class SessionResource implements SessionResourceInterface {

    @Inject
    HttpSession session;

    @Override
    public FraudModel get(String formUuid) {

        return (FraudModel) session.getAttribute(formUuid);
    }

    @Override
    public void save(String formUuid, FraudModel object) {

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

