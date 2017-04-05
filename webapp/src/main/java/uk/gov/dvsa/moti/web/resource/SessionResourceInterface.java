package uk.gov.dvsa.moti.web.resource;

import uk.gov.dvsa.moti.web.model.FraudModel;

public interface SessionResourceInterface {
    /**
     * Get fraud from session
     */
    public FraudModel get();

    /**
     * Save fraud to session
     */
    public void save(FraudModel object);

    /**
     * Remove fraud from session
     */
    public void remove();
}
