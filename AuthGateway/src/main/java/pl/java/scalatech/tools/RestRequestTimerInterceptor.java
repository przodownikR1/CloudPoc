package pl.java.scalatech.tools;

import java.io.IOException;

import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import com.codahale.metrics.Meter;
import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.Timer;

public class RestRequestTimerInterceptor implements ClientHttpRequestInterceptor {

    private MetricRegistry metricRegistry;

    public RestRequestTimerInterceptor(MetricRegistry metricRegistry) {
        this.metricRegistry = metricRegistry;
    }

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        Timer timer = metricRegistry.timer("request-timer");
        Timer.Context context = timer.time();
        Meter meter = metricRegistry.meter("request-meter");

        try {
            ClientHttpResponse response = execution.execute(request, body);
            return response;
        } finally {
            context.stop();
            meter.mark();
        }
    }
}