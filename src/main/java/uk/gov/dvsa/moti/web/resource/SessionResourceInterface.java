package uk.gov.dvsa.moti.web.resource;

import uk.gov.dvsa.moti.web.model.FraudInterface;

public interface SessionResourceInterface {
    public FraudInterface get(String formuuid);
    public void save(String formuuid, FraudInterface object);
    public void remove(String formUuid);
}
