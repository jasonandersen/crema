package crema.dao.web;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

/**
 * 
 * @author Jason Andersen (andersen.jason@gmail.com)
 */
public class GzipClientHttpRequestInterceptor implements ClientHttpRequestInterceptor {

    private static Logger log = LoggerFactory.getLogger(GzipClientHttpRequestInterceptor.class);

    /**
     * @see org.springframework.http.client.ClientHttpRequestInterceptor#intercept(
     *      org.springframework.http.HttpRequest, 
     *      byte[], 
     *      org.springframework.http.client.ClientHttpRequestExecution)
     */
    public ClientHttpResponse intercept(final HttpRequest request, final byte[] body,
            final ClientHttpRequestExecution execution)
            throws IOException {

        log.debug("intercepting client request");

        HttpHeaders headers = request.getHeaders();

        return execution.execute(request, body);
    }

}
