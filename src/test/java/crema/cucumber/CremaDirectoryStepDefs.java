package crema.cucumber;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.After;
import org.junit.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import crema.service.CremaDirectoryService;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

/**
 * Step definitions related to crema directory tests
 * 
 * @author Jason Andersen (andersen.jason@gmail.com)
 */
public class CremaDirectoryStepDefs extends AbstractCucumberStepDefs {

    private static Logger log = LoggerFactory.getLogger(CremaDirectoryStepDefs.class);

    @Autowired
    private CremaDirectoryService preferencesService;

    private File cremaDirectory;

    /*
     * test setup and teardown
     */

    @Before
    public void setup() {
        log.debug("setting up Cucumber step defs");
    }

    @After
    public void tearDown() {
        log.debug("tearing down Cucumber step defs");
        cremaDirectory.delete();
        log.debug("deleted crema directory: {}", cremaDirectory);
    }

    /*
     * WHEN steps
     */

    @When("^I open Crema$")
    public void I_open_Crema() throws Throwable {
        assertNotNull(preferencesService);
        cremaDirectory = preferencesService.getCremaDirectory();
    }

    /*
     * THEN steps
     */

    @Then("^I have a crema directory to store data in$")
    public void I_have_a_crema_directory_to_store_data_in() throws Throwable {
        assertNotNull(cremaDirectory);
        assertTrue(cremaDirectory.exists());
        assertTrue(cremaDirectory.isDirectory());
    }

    @Then("^I can read from the crema directory$")
    public void I_can_read_from_the_crema_directory() throws Throwable {
        assertTrue(cremaDirectory.canRead());
    }

    @Then("^I can write to the crema directory$")
    public void I_can_write_to_the_crema_directory() throws Throwable {
        assertTrue(cremaDirectory.canWrite());
    }

}
