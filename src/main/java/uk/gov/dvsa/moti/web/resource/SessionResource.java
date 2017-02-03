package uk.gov.dvsa.moti.web.resource;

import uk.gov.dvsa.moti.web.model.ModelInterface;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.servlet.http.HttpSession;

@Singleton
public class SessionResource implements SessionResourceInterface {

    @Inject
    HttpSession session;

    @Override
    public ModelInterface get(String formUuid) {
        return (ModelInterface) session.getAttribute(formUuid);
    }

    @Override
    public void save(String formUuid, ModelInterface object) {
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

