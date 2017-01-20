package uk.gov.dvsa.mot.intelligence.resource;

import uk.gov.dvsa.mot.intelligence.views.PersonView;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/fraud")
@Produces(MediaType.TEXT_HTML)
public class MotFraudResource {

    public MotFraudResource() {
    }

    @GET
    public PersonView getPerson() {
        return new PersonView("Andrzej");
    }
}