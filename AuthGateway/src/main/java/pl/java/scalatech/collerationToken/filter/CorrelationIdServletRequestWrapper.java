package pl.java.scalatech.collerationToken.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import pl.java.scalatech.collerationToken.CorrelationId;

public class CorrelationIdServletRequestWrapper extends HttpServletRequestWrapper {

    private String correlationId;

    public CorrelationIdServletRequestWrapper(HttpServletRequest request, String correlationId) {
        super(request);
        this.correlationId = correlationId;
    }

    @Override
    public String getHeader(String name) {
        if(CorrelationId.CORRELATION_ID_HEADER_KEY.equals(name)) {
            return correlationId;
        }
        return super.getHeader(name);
    }
}



