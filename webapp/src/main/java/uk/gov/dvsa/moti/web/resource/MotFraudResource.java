package uk.gov.dvsa.moti.web.resource;

import io.dropwizard.jersey.caching.CacheControl;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

import uk.gov.dvsa.moti.web.model.CsrfToken;
import uk.gov.dvsa.moti.web.model.FraudModel;
import uk.gov.dvsa.moti.web.routing.FraudRoutes;
import uk.gov.dvsa.moti.web.service.FraudService;
import uk.gov.dvsa.moti.web.views.FraudCookiePolicyView;
import uk.gov.dvsa.moti.web.views.FraudFormView;
import uk.gov.dvsa.moti.web.views.FraudSuccessView;
import uk.gov.dvsa.moti.web.views.FraudSummaryView;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.net.URI;
import java.util.Optional;

@Path(FraudRoutes.ROOT)
@Produces(MediaType.TEXT_HTML)
public class MotFraudResource {

    @Inject
    private FraudService fraudService;

    @GET
    @CacheControl(noStore = true)
    public Response displayForm(@Context CsrfToken csrfToken) {
        FraudFormView view = fraudService.displayForm();
        view.setCsrfToken(csrfToken.getCsrfToken());
        return Response.ok(view).build();
    }

    @POST
    public Response validateForm(@Context CsrfToken csrfToken, @BeanParam FraudModel model) {
        Optional<FraudFormView> view = fraudService.validateData(model);

        if (view.isPresent()) {
            view.get().setCsrfToken(csrfToken.getCsrfToken());
        }

        return createResponse(view, FraudRoutes.getSummaryUri());
    }

    @GET
    @CacheControl(noStore = true)
    @Path(FraudRoutes.SUMMARY)
    public Response displaySummary(@Context CsrfToken csrfToken) {
        Optional<FraudSummaryView> view = fraudService.displaySummary();
        if (view.isPresent()) {
            view.get().setCsrfToken(csrfToken.getCsrfToken());
        }
        return createResponse(view, FraudRoutes.getFormUri());
    }

    @POST
    @Path(FraudRoutes.SUMMARY)
    public Response sendReport(@CookieParam("csrf_token") String formUuid) {
        if (fraudService.sendReport(formUuid)) {
            return redirectTo(FraudRoutes.getReportUri());
        }

        return redirectTo(FraudRoutes.getFormUri());
    }

    @GET
    @CacheControl(noStore = true)
    @Path(FraudRoutes.REPORT)
    public Response displaySuccess(@Context HttpServletResponse response) {
        Optional<FraudSuccessView> optional = fraudService.displaySuccessPage(response);
        return createResponse(optional, FraudRoutes.getFormUri());
    }

    @GET
    @CacheControl(noStore = true)
    @Path(FraudRoutes.COOKIE_POLICY)
    public Response displayCookiePolicyPage() {
        return Response.ok(new FraudCookiePolicyView()).build();
    }

    private Response createResponse(Optional optional, URI uri) {
        if (optional.isPresent()) {
            return Response.ok(optional.get()).build();
        }

        return redirectTo(uri);
    }

    private Response redirectTo(URI uri) {
        return Response.seeOther(uri).build();
    }
}
