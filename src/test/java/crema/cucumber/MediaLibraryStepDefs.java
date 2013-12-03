package crema.cucumber;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.After;
import org.springframework.beans.factory.annotation.Autowired;

import crema.domain.MediaLibrary;
import crema.service.MediaLibraryService;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

/**
 * Step definitions related to media library tests.
 * 
 * @author Jason Andersen (andersen.jason@gmail.com)
 */
public class MediaLibraryStepDefs extends AbstractCucumberStepDefs {

    private File mediaDirectory;

    @Autowired
    private MediaLibraryService mediaLibraryService;

    private MediaLibrary mediaLibrary;

    /*
     * test setup and teardown
     */

    @After
    public void tearDownDirectory() {
        if (mediaDirectory != null && mediaDirectory.exists()) {
            mediaDirectory.delete();
        }
    }

    /*
     * GIVEN steps
     */

    @Given("^I have a directory$")
    public void I_have_a_directory() throws Throwable {
        mediaDirectory = buildTestMediaDirectory();
        assertNotNull(mediaDirectory);
    }

    @Given("^the directory exists$")
    public void the_directory_exists() throws Throwable {
        assertTrue(mediaDirectory.exists());
        assertTrue(mediaDirectory.isDirectory());
    }

    @Given("^the directory can be read$")
    public void the_directory_can_be_read() throws Throwable {
        assertTrue(mediaDirectory.canRead());
    }

    /*
     * WHEN steps
     */

    @When("^I choose the directory as a media library named \"([^\"]*)\"$")
    public void I_choose_the_directory_as_a_media_library_named(String libraryName) throws Throwable {
        mediaLibraryService.createMediaLibrary(mediaDirectory, libraryName);
    }

    /*
     * THEN steps
     */

    @Then("^I have a media library named \"([^\"]*)\"$")
    public void I_have_a_media_library_named(String libraryName) throws Throwable {
        mediaLibrary = mediaLibraryService.readMediaLibrary(libraryName);
        assertEquals(libraryName, mediaLibrary.getName());
    }

    @Then("^the directory for media library \"([^\"]*)\" matches the directory$")
    public void the_directory_for_media_library_matches_the_directory(String libraryName) throws Throwable {
        mediaLibrary = mediaLibraryService.readMediaLibrary(libraryName);
        assertEquals(mediaDirectory, mediaLibrary.getBaseDirectory());
    }

}
