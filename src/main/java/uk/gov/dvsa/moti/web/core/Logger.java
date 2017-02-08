package uk.gov.dvsa.moti.web.core;

import uk.gov.dvsa.mot.common.logging.LogFormatter;

import org.slf4j.LoggerFactory;

import java.io.PrintWriter;
import java.io.StringWriter;

public class Logger {

    private static org.slf4j.Logger logger = LoggerFactory.getLogger(Logger.class);

    public void error(Exception e, String errorId) {
        logger.error(LogFormatter.format(LogFormatter.Type.ERROR, errorId, e.toString() + getStackTrace(e)));
    }


    private String getStackTrace(Exception e){
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);
        return sw.toString();
    }
}
