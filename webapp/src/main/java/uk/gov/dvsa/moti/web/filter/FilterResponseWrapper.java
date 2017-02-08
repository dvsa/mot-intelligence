package uk.gov.dvsa.moti.web.filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

import org.apache.log4j.Logger;

public class FilterResponseWrapper extends HttpServletResponseWrapper {

    private final WrappedServletOutputStream output;
    private final PrintWriter writer;

    public FilterResponseWrapper(HttpServletResponse response) throws IOException {
        super(response);
        output = new WrappedServletOutputStream(response.getOutputStream());
        writer = new PrintWriter(output, true);
    }
    @Override
    public ServletOutputStream getOutputStream() throws IOException {
        return output;
    }
    @Override
    public PrintWriter getWriter() throws IOException {
        return writer;
    }
    @Override
    public void flushBuffer() throws IOException {
        writer.flush();
    }
}
