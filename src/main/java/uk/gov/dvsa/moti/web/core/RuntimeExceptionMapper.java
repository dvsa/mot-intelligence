package uk.gov.dvsa.moti.web.core;

import uk.gov.dvsa.mot.common.logging.LogFormatter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import uk.gov.dvsa.moti.web.views.ErrorView;

import java.util.UUID;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public class RuntimeExceptionMapper implements ExceptionMapper<Exception> {

    private static Logger logger = LoggerFactory.getLogger(RuntimeExceptionMapper.class);

    @Override
    public Response toResponse(Exception e) {

        Response.Status responseStatus = Response.Status.INTERNAL_SERVER_ERROR;
        String errorId = UUID.randomUUID().toString();

        if(e instanceof NotFoundException) {
            responseStatus = Response.Status.NOT_FOUND;
            log404Exception(e, errorId);
        } else {
            log500Exception(e, errorId);
        }

        ErrorView view = new ErrorView(String.valueOf(responseStatus.getStatusCode()));
        view.setErrorId(errorId);

        return Response.status(responseStatus.getStatusCode())
                .entity(view)
                .build();
    }

    private void log404Exception(Exception e, String errorId) {
        logger.info(LogFormatter.format(LogFormatter.Type.ERROR, "404 not found " + errorId, e.toString()));
    }

    private void log500Exception(Exception e, String errorId) {
        logger.error(LogFormatter.format(LogFormatter.Type.ERROR, "Internal server error " + errorId, e.toString()));
    }
}