package crema.cucumber;

import org.junit.AfterClass;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import crema.test.IntegrationTest;
import crema.util.BeanContext;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

/**
 * JUnit runner class for cucumber tests.
 * 
 * @author Jason Andersen (andersen.jason@gmail.com)
 */
//@Ignore
@RunWith(Cucumber.class)
@CucumberOptions(format = "pretty")
@Category(IntegrationTest.class)
public class CucumberJUnitRunnerTest {

    private static Logger log = LoggerFactory.getLogger(CucumberJUnitRunnerTest.class);

    @AfterClass
    public static void tearDown() {
        /*
         * For reasons I can't figure out, the Cucumber tests seem to dirty up the context. Since we're not
         * using a Spring JUnit runner, we can't use the @DirtiesContext annotation so we're just going to
         * programmatically refresh the context after the Cucumber tests are done to make sure they don't
         * interfere with any following tests.
         */
        log.info("cleaning up after Cucumber tests");
        BeanContext.refreshContext();
    }
}
