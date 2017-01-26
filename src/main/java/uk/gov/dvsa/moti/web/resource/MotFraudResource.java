package uk.gov.dvsa.moti.web.resource;

import uk.gov.dvsa.moti.web.form.FraudForm;
import uk.gov.dvsa.moti.web.model.FraudModel;
import uk.gov.dvsa.moti.web.views.FraudFormView;
import uk.gov.dvsa.moti.web.views.FraudSummaryView;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import java.net.URI;
import java.util.UUID;

@Path("/fraud")
@Produces(MediaType.TEXT_HTML)
public class MotFraudResource {

    public MotFraudResource() {
    }

    @GET
    public FraudFormView displayForm(@QueryParam("formUuid") String formUuid, @BeanParam SessionResource sessionResource) {
        FraudForm form = getFraudForm(formUuid, sessionResource);

        return new FraudFormView(form);
    }

    @POST
    public Response validateForm( @QueryParam("formUuid") String formUuid, @BeanParam FraudModel fraud, @BeanParam SessionResource sessionResource) {
        FraudForm form = new FraudForm(fraud);

        if (form.isValid()) {
            formUuid = generateUuidIfNull(formUuid);
            sessionResource.save(formUuid, fraud);

            return redirectTo("fraud/summary",formUuid);
        }

        return Response.ok(new FraudFormView(form)).build();
    }

    @GET
    @Path("summary")
    public Response displaySummary(@QueryParam("formUuid") String formUuid, @BeanParam SessionResource sessionResource) {
        FraudModel model = getModel(formUuid, sessionResource);
        FraudForm form = new FraudForm(model);
        if (form.isValid() == false) {
            formUuid = generateUuidIfNull(formUuid);
            return redirectTo("/fraud", formUuid);
        }

        return Response.ok(new FraudSummaryView()).build();
    }

    private Response redirectTo(String path, String formUuid) {
        URI uri = UriBuilder.fromUri(path).queryParam("formUuid", formUuid).build();
        return Response.seeOther(uri).build();
    }

    private Response redirectTo(String path) {
        URI uri = UriBuilder.fromUri(path).build();
        return Response.seeOther(uri).build();
    }

    private FraudForm getFraudForm(String formUuid, SessionResourceInterface sessionResource) {
        FraudModel fraudModel = getModel(formUuid, sessionResource);

        return new FraudForm(fraudModel);
    }

    private FraudModel getModel(String formUuid, SessionResourceInterface sessionResource) {
        FraudModel model = (FraudModel) sessionResource.get((formUuid));
        if (model == null) {
            model = new FraudModel();
        }

        return model;
    }

    private String generateUuidIfNull(String formUuid) {
        if (formUuid == null) {
            formUuid = UUID.randomUUID().toString();
        }
        return formUuid;
    }
}
