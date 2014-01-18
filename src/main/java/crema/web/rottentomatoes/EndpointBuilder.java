package crema.web.rottentomatoes;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.springframework.stereotype.Component;

/**
 * Builds out endpoint URLs for calls to the Rotten Tomatoes API.
 * 
 * @author Jason Andersen (andersen.jason@gmail.com)
 */
@Component
public class EndpointBuilder {

    private static final String URL_ENCODING = "UTF-8";

    /*
     * TODO - these should really be placed in an application properties file at some point
     */

    private String endpointPrefix = "http://api.rottentomatoes.com/api/public/v1.0";

    private String pingEndpoint = "%s/lists.json?apikey=%s";

    private String searchEndpoint = "%s/movies.json?q=%s&page_limit=10&page=1&apikey=%s";

    private String moviesEndpoint = "%s/movies/%d.json?apikey=%s";

    private String apiKey = "sp6wwt968extpfnpd4kx97pc";

    /**
     * @param query
     * @return a properly built URL to execute a search against the Rotten Tomatoes API
     */
    public String buildSearchUrl(final String query) {
        String cleansed = cleanQuery(query);
        return String.format(searchEndpoint, endpointPrefix, cleansed, apiKey);
    }

    /**
     * @param movieId
     * @return a properly built URL to execute 
     */
    public String buildMovieUrl(final int movieId) {
        return String.format(moviesEndpoint, endpointPrefix, movieId, apiKey);
    }

    /**
     * @return a properly built URL to execute a ping against the Rotten Tomatoes service
     */
    public String buildPingUrl() {
        return String.format(pingEndpoint, endpointPrefix, apiKey);
    }

    /**
     * Clean up the query string and prepare it for being passed in to a URL.
     * @param query
     * @return cleansed query string
     */
    private String cleanQuery(final String query) {
        String cleansed = query.toLowerCase();
        try {
            return URLEncoder.encode(cleansed, URL_ENCODING);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Allow tests to set an endpoint prefix.
     * @param endpointPrefix
     */
    protected void setEndpointPrefix(final String endpointPrefix) {
        this.endpointPrefix = endpointPrefix;
    }

    /**
     * Allow tests to set an API key.
     * @param apiKey
     */
    protected void setApiKey(final String apiKey) {
        this.apiKey = apiKey;
    }

}
