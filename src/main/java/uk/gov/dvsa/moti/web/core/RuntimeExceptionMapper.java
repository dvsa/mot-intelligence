package uk.gov.dvsa.moti.web.core;

import uk.gov.dvsa.moti.web.views.ErrorView;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import java.util.UUID;

public class RuntimeExceptionMapper implements ExceptionMapper<Exception> {
    @Override
    public Response toResponse(Exception e) {

        Response.Status responseStatus = Response.Status.INTERNAL_SERVER_ERROR;

        if(e instanceof NotFoundException) {
            responseStatus = Response.Status.NOT_FOUND;
        }

        ErrorView view = new ErrorView(String.valueOf(responseStatus.getStatusCode()));
        view.setErrorId(UUID.randomUUID().toString());

        return Response.status(responseStatus.getStatusCode())
                .entity(view)
                .build();
    }
}