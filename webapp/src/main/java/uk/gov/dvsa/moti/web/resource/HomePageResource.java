package uk.gov.dvsa.moti.web.resource;

import uk.gov.dvsa.moti.web.routing.FraudRoutes;
import uk.gov.dvsa.moti.web.views.HomeView;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

@Path("/")
@Produces(MediaType.TEXT_HTML)
public class HomePageResource {

    /**
     * Display homepage
     * @return
     */
    @GET
    public Response display() {
        return Response.ok(new HomeView(FraudRoutes.getFormPath())).build();
    }
}
