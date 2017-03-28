package uk.gov.dvsa.moti.web.core;

import org.slf4j.*;
import uk.gov.dvsa.moti.web.views.ErrorView;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

/**
 * Exception mapper being used at Jersey level to handle exceptions in application.
 */
public class MotiExceptionMapper implements ExceptionMapper<Exception> {
    private static org.slf4j.Logger logger = LoggerFactory.getLogger(ExceptionMapper.class);

    @Override
    public Response toResponse(Exception e) {
        Response.Status responseStatus = (e instanceof NotFoundException) ?
                Response.Status.NOT_FOUND :
                Response.Status.INTERNAL_SERVER_ERROR;

        logger.error("Exception encountered", e);

        ErrorView view = new ErrorView(String.valueOf(responseStatus.getStatusCode()));

        return Response.status(responseStatus.getStatusCode())
                .entity(view)
                .build();
    }

}