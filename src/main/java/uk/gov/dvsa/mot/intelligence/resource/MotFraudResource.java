package uk.gov.dvsa.mot.intelligence.resource;

import org.hibernate.validator.constraints.NotEmpty;
import uk.gov.dvsa.mot.intelligence.form.FraudForm;
import uk.gov.dvsa.mot.intelligence.model.FraudModel;
import uk.gov.dvsa.mot.intelligence.views.FraudFormView;
import uk.gov.dvsa.mot.intelligence.views.FraudSummaryView;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import java.net.URI;

@Path("/fraud")
@Produces(MediaType.TEXT_HTML)
public class MotFraudResource {

    public MotFraudResource() {
    }

    @GET
    public FraudFormView displayForm(@QueryParam("formUuid") String formUuid) {
        FraudForm form = new FraudForm(new FraudModel());
        return new FraudFormView(form);
    }

    @POST
    public Response validateForm(@BeanParam FraudModel fraud) {
        FraudForm form = new FraudForm(fraud);

        if (form.isValid()) {
            //save data

            return redirectTo("fraud/summary");
        }

        return Response.ok(new FraudFormView(form)).build();
    }

    @GET
    @Path("/summary")
    public Response displaySummary(@QueryParam("formUuid") String formUuid) {
        FraudForm form = new FraudForm(new FraudModel());
        if (form.isValid() == false) {
            return redirectTo("/fraud");
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
}