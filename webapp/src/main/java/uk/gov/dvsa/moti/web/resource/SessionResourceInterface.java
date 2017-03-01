package uk.gov.dvsa.moti.web.resource;

import uk.gov.dvsa.moti.web.model.FraudModel;

public interface SessionResourceInterface {
    public FraudModel get();
    public void save(FraudModel object);
    public void remove();
}
