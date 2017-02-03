package uk.gov.dvsa.moti.web.service;

import uk.gov.dvsa.moti.web.form.FraudForm;
import uk.gov.dvsa.moti.web.model.FraudModel;
import uk.gov.dvsa.moti.web.resource.SessionResourceInterface;
import uk.gov.dvsa.moti.web.views.FraudFormView;
import uk.gov.dvsa.moti.web.views.FraudSuccessView;
import uk.gov.dvsa.moti.web.views.FraudSummaryView;

import javax.inject.Inject;
import javax.ws.rs.core.UriBuilder;
import java.net.URI;
import java.util.Optional;
import java.util.UUID;

public class FraudService {

    private SessionResourceInterface sessionResource;

    @Inject
    public FraudService(SessionResourceInterface sessionResource) {
        this.sessionResource = sessionResource;
    }

    public FraudFormView displayForm(String formUuid) {
        FraudModel model = getModel(formUuid);
        return new FraudFormView(createForm(model));
    }

    public Optional<FraudFormView> validateData(FraudModel model, String formUuid) {
        FraudForm form = createForm(model);
        if (form.isValid()) {
            sessionResource.save(formUuid, model);

            return Optional.empty();
        }

        return Optional.of(new FraudFormView(form));
    }

    public Optional<FraudSummaryView> displaySummary(String formUuid) {
        if (isDataValid(formUuid)) {
            FraudModel model = getModel(formUuid);
            String backLink = getUri("/fraud", formUuid).toString();
            return Optional.of(new FraudSummaryView(model, backLink));
        }

        return Optional.empty();
    }

    public boolean sendReport(String formUuid) {
        if (isDataValid(formUuid)) {
            //generate and send xml
            return true;
        }

        return false;
    }

    public Optional<FraudSuccessView> displaySuccessPage(String formUuid){
        if (isDataValid(formUuid)) {
            sessionResource.remove(formUuid);
            return Optional.of(new FraudSuccessView());
        }

        return Optional.empty();
    }

    private FraudForm createForm(FraudModel model) {
        return new FraudForm(model);
    }

    private boolean isDataValid(String formUuid) {
        FraudModel model = getModel(formUuid);
        return createForm(model).isValid();
    }

    private FraudModel getModel(String formUuid) {
        FraudModel model = (FraudModel) sessionResource.get((formUuid));
        if (model == null) {
            model = new FraudModel();
        }

        return model;
    }

    private URI getUri(String path, String formUuid) {
        return UriBuilder.fromUri(path).queryParam("formUuid", formUuid).build();
    }
}
