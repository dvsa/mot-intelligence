package uk.gov.dvsa.moti.web.filter;

import java.io.FilterOutputStream;
import java.io.IOException;

import javax.servlet.ServletOutputStream;
import javax.servlet.WriteListener;

public class WrappedServletOutputStream extends ServletOutputStream {
    private final FilterOutputStream output;

    public WrappedServletOutputStream(ServletOutputStream output) {
        this.output = new FilterOutputStream(output);
    }
    @Override
    public void write(int b) throws IOException {
        output.write(b);
    }
    @Override
    public void flush() throws IOException {
        output.flush();
    }

    @Override
    public boolean isReady() {
        return true;
    }

    @Override
    public void setWriteListener(WriteListener writeListener) {

    }
}
