package crema.dao.web.rottentomatoes;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

/**
 * Testing the {@link EndpointBuilder} class.
 * 
 * @author Jason Andersen (andersen.jason@gmail.com)
 */
public class EndpointBuilderTest {

    private static final String ENDPOINT_PREFIX = "http://api.rottentomatoes.com/api/public/v1.0";

    private static final String API_KEY = "APIKEY";

    private EndpointBuilder builder;

    @Before
    public void setup() {
        builder = new EndpointBuilder();
        builder.setEndpointPrefix(ENDPOINT_PREFIX);
        builder.setApiKey(API_KEY);
    }

    @Test
    public void testTestUrl() {
        String expectedUrl = "http://api.rottentomatoes.com/api/public/v1.0/lists.json?apikey=APIKEY";
        String url = builder.buildPingUrl();
        assertEquals(expectedUrl, url);
    }

    @Test
    public void testSearchUrl() {
        String url = builder.buildSearchUrl("query");
        String expectedUrl = "http://api.rottentomatoes.com/api/public/v1.0/movies.json?q=query&page_limit=10&page=1&apikey=APIKEY";
        assertEquals(expectedUrl, url);
    }

    @Test
    public void testSearchUrlQueryToLowerCase() {
        String url = builder.buildSearchUrl("QUERY");
        String expectedUrl = "http://api.rottentomatoes.com/api/public/v1.0/movies.json?q=query&page_limit=10&page=1&apikey=APIKEY";
        assertEquals(expectedUrl, url);
    }

    @Test
    public void testSearchUrlMultipleWords() {
        String url = builder.buildSearchUrl("two words");
        String expectedUrl = "http://api.rottentomatoes.com/api/public/v1.0/movies.json?q=two+words&page_limit=10&page=1&apikey=APIKEY";
        assertEquals(expectedUrl, url);
    }

    @Test
    public void testMovieUrl() {
        String url = builder.buildMovieUrl(12345);
        String expectedUrl = "http://api.rottentomatoes.com/api/public/v1.0/movies/12345.json?apikey=APIKEY";
        assertEquals(expectedUrl, url);
    }

}
