package crema.dao.web.rottentomatoes;

import static org.junit.Assert.assertTrue;
import static org.junit.Assume.assumeTrue;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import crema.domain.Movie;
import crema.test.AbstractIntegrationTest;

/**
 * Runs live integration tests against the Rotten Tomatoes API web service.
 * 
 * @author Jason Andersen (andersen.jason@gmail.com)
 */
public class RottenTomatoesIntegrationTest extends AbstractIntegrationTest {

    private static Logger log = LoggerFactory.getLogger(RottenTomatoesIntegrationTest.class);

    private static Boolean isRottenTomatoesSiteAvailable;

    @Autowired
    private RottenTomatoesMovieAttributesProvider provider;

    @Autowired
    private EndpointBuilder endpointBuilder;

    private Movie movie;

    /**
     * If the rotten tomatoes site can't be acccessed, skip these tests.
     */
    @Before
    public void setupTests() {
        assumeTrue(canAccessRottenTomatoesSite());
        movie = new Movie();
        movie.setName("Toy Story 3");
    }

    @Test
    public void test() {
        assertTrue(movie.getAllAttributes().isEmpty());
        provider.decorateMovie(movie);
        assertTrue(!movie.getAllAttributes().isEmpty());
    }

    /**
     * @return true if we can access www.rottentomatoes.com while running this test
     */
    private boolean canAccessRottenTomatoesSite() {
        /*
         * any time we can't access the RT website, we want to skip all these tests, not fail them
         */
        if (isRottenTomatoesSiteAvailable == null) {
            isRottenTomatoesSiteAvailable = ping(endpointBuilder.buildPingUrl(), 1000);
        }
        if (!isRottenTomatoesSiteAvailable) {
            log.warn("cannot access Rotten Tomatoes site - skipping test");
        }
        return isRottenTomatoesSiteAvailable;
    }

}
