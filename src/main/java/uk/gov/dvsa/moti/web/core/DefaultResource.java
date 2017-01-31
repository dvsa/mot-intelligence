package uk.gov.dvsa.moti.web.core;

import io.dropwizard.views.View;
import uk.gov.dvsa.moti.web.views.ErrorView;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.net.URISyntaxException;

@Path("/")
public class DefaultResource {

  /**
   * Default resource method which catches unmatched resource requests. A page not found view is
   * returned.
   */
  @Path("/{default: .*}")
  @GET
  public View defaultMethod() throws URISyntaxException {
    // Return a page not found view.
    View pageNotFoundView = new ErrorView("404");
    return pageNotFoundView;
  }

}