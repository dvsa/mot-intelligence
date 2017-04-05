package uk.gov.dvsa.moti.web.service;

import uk.gov.dvsa.moti.fraudserializer.xml.Fraud;
import uk.gov.dvsa.moti.web.cookie.Cookies;
import uk.gov.dvsa.moti.web.filter.CsrfTokenFilter;
import uk.gov.dvsa.moti.web.form.FraudForm;
import uk.gov.dvsa.moti.web.fraudSender.mapper.XmlFraudMapper;
import uk.gov.dvsa.moti.web.model.CsrfToken;
import uk.gov.dvsa.moti.web.model.FraudModel;
import uk.gov.dvsa.moti.web.fraudSender.FraudSender;
import uk.gov.dvsa.moti.web.resource.SessionResourceInterface;
import uk.gov.dvsa.moti.web.routing.FraudRoutes;
import uk.gov.dvsa.moti.web.views.FraudFormView;
import uk.gov.dvsa.moti.web.views.FraudSuccessView;
import uk.gov.dvsa.moti.web.views.FraudSummaryView;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

public class FraudService {

    private SessionResourceInterface sessionResource;

    private FraudSender fraudSender;

    @Inject
    public FraudService(
            SessionResourceInterface sessionResource,
            FraudSender fraudSender
    ) {
        this.sessionResource = sessionResource;
        this.fraudSender = fraudSender;
    }

    /**
     * Display form
     * @return
     */
    public FraudFormView displayForm() {
        FraudModel model = getModel();
        return new FraudFormView(createForm(model));
    }

    /**
     * Validate data
     * @param model
     * @return
     */
    public Optional<FraudFormView> validateData(FraudModel model) {
        FraudForm form = createForm(model);
        if (form.isValid()) {
            sessionResource.save(model);

            return Optional.empty();
        }

        return Optional.of(new FraudFormView(form));
    }

    /**
     * Display summary view when data is valid
     * @return
     */
    public Optional<FraudSummaryView> displaySummary() {
        if (isDataValid()) {
            FraudModel model = getModel();
            String backLink = FraudRoutes.getFormPath();
            return Optional.of(new FraudSummaryView(model, backLink));
        }

        return Optional.empty();
    }

    /**
     * Send report
     * @param formUuid
     * @return
     */
    public boolean sendReport(String formUuid) {
        if (isDataValid()) {
            FraudModel model = getModel();
            Fraud xmlFraudModel = XmlFraudMapper.getXmlFraudModel(model, formUuid);
            fraudSender.send(xmlFraudModel);
            return true;
        }

        return false;
    }

    /**
     * Display success page
     * @param response
     * @return
     */
    public Optional<FraudSuccessView> displaySuccessPage(HttpServletResponse response){
        if (isDataValid()) {
            sessionResource.remove();
            Cookies.removeCookie(response, CsrfTokenFilter.COOKIE_NAME);
            return Optional.of(new FraudSuccessView());
        }

        return Optional.empty();
    }

    /**
     * Create form
     * @param model
     * @return
     */
    private FraudForm createForm(FraudModel model) {
        return new FraudForm(model);
    }

    /**
     * Validate data
     * @return
     */
    private boolean isDataValid() {
        FraudModel model = getModel();
        return createForm(model).isValid();
    }

    /**
     * Get model from session or create new model
     * @return
     */
    private FraudModel getModel() {
        FraudModel model = sessionResource.get();
        if (model == null) {
            model = new FraudModel();
        }

        return model;
    }
}
