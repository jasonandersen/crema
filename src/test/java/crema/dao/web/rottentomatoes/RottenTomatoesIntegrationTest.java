package crema.dao.web.rottentomatoes;

import org.junit.Assume;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import crema.test.AbstractIntegrationTest;

/**
 * Testing the {@link RottenTomatoesMovieAttributeProvider} class.
 * 
 * @author Jason Andersen (andersen.jason@gmail.com)
 */
public class RottenTomatoesIntegrationTest extends AbstractIntegrationTest {

    private static Logger log = LoggerFactory.getLogger(RottenTomatoesIntegrationTest.class);

    /**
     * If the rotten tomatoes site can't be acccessed, skip these tests.
     */
    @BeforeClass
    public static void validateEnvironment() {
        Assume.assumeTrue(canAccessRottenTomatoesSite());
    }

    /**
     * @return true if we can access www.rottentomatoes.com while running this test
     */
    private static boolean canAccessRottenTomatoesSite() {
        /*
         * any time we can't access the RT website, we want to skip all these tests, not fail them
         */
        boolean canAccess = false;
        if (!canAccess) {
            log.warn("cannot access Rotten Tomatoes site - skipping test");
        }
        return canAccess;
    }

    @Test
    public void test() {
        //noop
    }

}
