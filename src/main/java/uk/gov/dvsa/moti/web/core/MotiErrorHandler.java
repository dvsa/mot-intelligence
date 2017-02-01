package uk.gov.dvsa.moti.web.core;

import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Template;
import com.github.jknack.handlebars.io.ClassPathTemplateLoader;
import com.github.jknack.handlebars.io.TemplateLoader;

import org.eclipse.jetty.server.Response;
import org.eclipse.jetty.server.handler.ErrorHandler;

import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

public class MotiErrorHandler extends ErrorHandler {
    private static Logger logger = new Logger();

    protected void writeErrorPage(HttpServletRequest request, Writer writer, int code, String message, boolean showStacks)
            throws IOException {
        writer.write(renderErrorView(code));
    }

    private String renderErrorView(int code) {
        TemplateLoader loader = new ClassPathTemplateLoader();

        Handlebars handlebars = new Handlebars(loader);
        try {
            String errorTemplate = code == Response.SC_NOT_FOUND ? "404" : "500";
            Template template = handlebars.compile("/uk/gov/dvsa/moti/web/views/core/error/" + errorTemplate);

            String errorId = UUID.randomUUID().toString();

            Map<String,String> context = new HashMap<>();
            context.put("errorId", errorId);
            logger.info(code + " resource not found" , errorId);

            return template.apply(context);
        } catch (IOException e) {
            return e.toString();
        }
    }
}