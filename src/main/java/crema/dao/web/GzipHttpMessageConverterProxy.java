package crema.dao.web;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.zip.GZIPInputStream;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;

/**
 * NOTE: this is copied out of the comments thread of Spring's JIRA ticket SPR-7874
 * https://jira.springsource.org/browse/SPR-7874
 * 
 * A simple implementation of the {@link HttpMessageConverter} interface which has the ability to determine that a given
 * response is compressed with Gzip or not. If the response is compressed a new HttpInputMessage will be delegated to
 * the underlying converter. That HttpInputMessage contains a {@link GZIPInputStream} rather than a regular InputStream
 *
 * @author balint
 * @version 1.0
 * @see GZIPInputStream
 * @since 07 08 2013
 */
public class GzipHttpMessageConverterProxy implements HttpMessageConverter<Object> {

    private HttpMessageConverter<Object> converter;

    /**
     * Initialize a new GzipHttpMessageConverterProxy for a given HttpMessageConverter.
     *
     * @param converter converter to be proxied
     */
    @SuppressWarnings("unchecked")
    public GzipHttpMessageConverterProxy(final HttpMessageConverter<?> converter) {
        super();
        this.converter = (HttpMessageConverter<Object>) converter;
    }

    /**
     * {@inheritDoc}
     *
     * @param clazz     the class to test for readability
     * @param mediaType the media type to read, can be null if not specified. Typically the value of a Content-Type header.
     * @return true if data can be read otherwise false
     */
    @SuppressWarnings("rawtypes")
    public boolean canRead(final Class clazz, final MediaType mediaType) {
        return converter.canRead(clazz, mediaType);
    }

    /**
     * @see org.springframework.http.converter.HttpMessageConverter#canWrite(java.lang.Class, org.springframework.http.MediaType)
     */
    @SuppressWarnings("rawtypes")
    public boolean canWrite(final Class clazz, final MediaType mediaType) {
        return converter.canWrite(clazz, mediaType);
    }

    /**
     * @see org.springframework.http.converter.HttpMessageConverter#getSupportedMediaTypes()
     */
    public List<MediaType> getSupportedMediaTypes() {
        return converter.getSupportedMediaTypes();
    }

    /**
     * {@inheritDoc}
     * In addition this method checks if the response is compressed or not. If it is, a new 
     * {@link HttpInputMessageProxy HttpInputMessageProxy}.
     * HttpInputMessage will be delegated to the proxying converter otherwise a simple delegation is invoked
     *
     * @return delegated method return value
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public Object read(final Class clazz, final HttpInputMessage inputMessage) throws IOException {
        HttpInputMessage inputMessageProxy = inputMessage;

        if (isCompressed(inputMessage.getHeaders())) {
            inputMessageProxy = new HttpInputMessageProxy(new GZIPInputStream(inputMessage.getBody()),
                    inputMessage.getHeaders());
        }
        return converter.read(clazz, inputMessageProxy);
    }

    /**
     * @see org.springframework.http.converter.HttpMessageConverter#write(
     *      java.lang.Object, org.springframework.http.MediaType, org.springframework.http.HttpOutputMessage)
     */
    public void write(final Object o, final MediaType contentType, final HttpOutputMessage outputMessage)
            throws IOException {
        converter.write(o, contentType, outputMessage);
    }

    /**
     * Determine if the request/response header has gzip compression set. If GZIP enabled true will be returned otherwise false
     *
     * @param headers headers to check for the compression
     * @return true if compressed otherwise false
     */
    private boolean isCompressed(final HttpHeaders headers) {
        for (String headerKey : headers.keySet()) {
            final List<String> headerValues = headers.get(headerKey);
            if (headerValues.contains("gzip")) {
                return true;
            }
        }
        return false;
    }

    /**
     * Simple proxy class which proxies a given input stream and a given HttpHeaders object. This class is used when the
     * original HttpInputMessage has been changed to delegate a new {@link GZIPInputStream} to the underlying converter.
     *
     * @see HttpInputMessage
     */
    private class HttpInputMessageProxy implements HttpInputMessage {
        private InputStream is;
        private HttpHeaders headers;

        private HttpInputMessageProxy(final InputStream is, final HttpHeaders headers) {
            this.is = is;
            this.headers = headers;
        }

        public InputStream getBody() throws IOException {
            return is;
        }

        public HttpHeaders getHeaders() {
            return headers;
        }
    }
}
