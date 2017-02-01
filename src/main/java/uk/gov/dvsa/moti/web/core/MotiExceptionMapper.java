package uk.gov.dvsa.moti.web.core;

import uk.gov.dvsa.moti.web.views.ErrorView;

import java.util.UUID;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public class MotiExceptionMapper implements ExceptionMapper<Exception> {

    private static Logger logger = new Logger();

    @Override
    public Response toResponse(Exception e) {

        Response.Status responseStatus = Response.Status.INTERNAL_SERVER_ERROR;
        String errorId = UUID.randomUUID().toString();

        if(e instanceof NotFoundException) {
            responseStatus = Response.Status.NOT_FOUND;
            logger.info(e, errorId);
        } else {
            logger.error(e, errorId);
        }

        ErrorView view = new ErrorView(String.valueOf(responseStatus.getStatusCode()));
        view.setErrorId(errorId);

        return Response.status(responseStatus.getStatusCode())
                .entity(view)
                .build();
    }

}