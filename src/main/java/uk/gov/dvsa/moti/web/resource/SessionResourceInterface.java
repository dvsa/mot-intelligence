package uk.gov.dvsa.moti.web.resource;

import uk.gov.dvsa.moti.web.model.ModelInterface;

public interface SessionResourceInterface {
    public ModelInterface get(String formuuid);
    public void save(String formuuid, ModelInterface object);
    public void remove(String formUuid);
}
