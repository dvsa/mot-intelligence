package uk.gov.dvsa.moti.web.resource;

import uk.gov.dvsa.moti.web.model.FraudModel;

public interface SessionResourceInterface {
    public FraudModel get(String formuuid);
    public void save(String formuuid, FraudModel object);
    public void remove(String formUuid);
}
