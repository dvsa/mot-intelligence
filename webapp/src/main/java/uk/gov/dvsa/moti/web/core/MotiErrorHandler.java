package uk.gov.dvsa.moti.web.core;

import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Template;
import com.github.jknack.handlebars.io.ClassPathTemplateLoader;
import com.github.jknack.handlebars.io.TemplateLoader;

import org.eclipse.jetty.server.Response;
import org.eclipse.jetty.server.handler.ErrorHandler;
import org.slf4j.*;

import java.io.IOException;
import java.io.Writer;

import javax.servlet.http.HttpServletRequest;

public class MotiErrorHandler extends ErrorHandler {
    private static org.slf4j.Logger logger = LoggerFactory.getLogger(MotiErrorHandler.class);

    protected void writeErrorPage(HttpServletRequest request, Writer writer, int code, String message, boolean showStacks)
            throws IOException {
        writer.write(renderErrorView(code));
    }

    private String renderErrorView(int code) {
        TemplateLoader loader = new ClassPathTemplateLoader();

        Handlebars handlebars = new Handlebars(loader);
        try {
            String errorTemplate = code == Response.SC_NOT_FOUND ? "404" : "500";
            Template template = handlebars.compile(TemplatePaths.buildErrorTemplatePath(errorTemplate));

            return template.apply(null);
        } catch (IOException e) {
            logger.error("An unexpected exception was encountered", e);
            throw new RuntimeException(e);
        }
    }
}