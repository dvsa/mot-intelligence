package uk.gov.dvsa.moti.web.resource;

import org.hibernate.validator.constraints.NotEmpty;

import javax.ws.rs.core.Response;

import uk.gov.dvsa.moti.web.model.FraudModel;
import uk.gov.dvsa.moti.web.service.FraudService;
import uk.gov.dvsa.moti.web.views.FraudCookiePolicyView;
import uk.gov.dvsa.moti.web.views.FraudFormView;
import uk.gov.dvsa.moti.web.views.FraudSuccessView;
import uk.gov.dvsa.moti.web.views.FraudSummaryView;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;
import java.net.URI;
import java.util.Optional;
import java.util.UUID;

@Path("/fraud")
@Produces(MediaType.TEXT_HTML)
public class MotFraudResource {

    @Inject
    private FraudService fraudService;

    @GET
    public Response displayForm(@QueryParam("formUuid") String formUuid) {
        FraudFormView view = fraudService.displayForm(formUuid);
        return Response.ok(view).build();
    }

    @POST
    public Response validateForm(@QueryParam("formUuid") String formUuid, @BeanParam FraudModel model) {
        formUuid = generateUuidIfNull(formUuid);
        Optional<FraudFormView> optional = fraudService.validateData(model, formUuid);

        return createResponse(optional, "/fraud/summary", formUuid);
    }

    @GET
    @Path("summary")
    public Response displaySummary(@NotEmpty @QueryParam("formUuid") String formUuid) {
        Optional<FraudSummaryView> optional = fraudService.displaySummary(formUuid);
        return createResponse(optional, "/fraud", formUuid);
    }

    @POST
    @Path("summary")
    public Response sendReport(@NotEmpty @QueryParam("formUuid") String formUuid) {
        if (fraudService.sendReport(formUuid)) {
            return redirectTo("/fraud/report", formUuid);
        }

        return redirectTo("/fraud", formUuid);
    }

    @GET
    @Path("report")
    public Response displaySuccess(@NotEmpty @QueryParam("formUuid") String formUuid) {
        Optional<FraudSuccessView> optional = fraudService.displaySuccessPage(formUuid);
        return createResponse(optional, "/fraud", formUuid);
    }

    @GET
    @Path("cookie-policy")
    public Response displayCookiePolicyPage() {
        return Response.ok(new FraudCookiePolicyView()).build();
    }

    private Response createResponse(Optional optional, String redirectUrl, String formUuid) {
        if (optional.isPresent()) {
            return Response.ok(optional.get()).build();
        }

        return redirectTo(redirectUrl, formUuid);
    }

    private URI getUri(String path, String formUuid) {
        return UriBuilder.fromUri(path).queryParam("formUuid", formUuid).build();
    }

    private Response redirectTo(String path, String formUuid) {
        return Response.seeOther(getUri(path, formUuid)).build();
    }

    private String generateUuidIfNull(String formUuid) {
        if (formUuid == null) {
            formUuid = UUID.randomUUID().toString();
        }
        return formUuid;
    }
}
