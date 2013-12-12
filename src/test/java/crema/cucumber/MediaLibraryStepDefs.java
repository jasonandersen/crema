package crema.cucumber;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.After;
import org.junit.Before;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;

import crema.domain.MediaLibrary;
import crema.exception.DuplicateMediaLibraryException;
import crema.exception.InvalidMediaLibraryDirectoryException;
import crema.service.MediaLibraryService;
import crema.test.TestUtil;
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

    private Exception exception;

    /*
     * test setup and teardown
     */

    @Before
    public void setup() {
        mediaLibrary = null;
        exception = null;
    }

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
        mediaDirectory = TestUtil.buildTestMediaDirectory(getClass());
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

    @Given("^I have an existing media library named \"([^\"]*)\"$")
    public void I_have_an_existing_media_library_named(String libraryName) throws Throwable {
        mediaDirectory = TestUtil.buildTestMediaDirectory(getClass());
        mediaLibraryService.createMediaLibrary(mediaDirectory, libraryName);
    }

    @Given("^I have an existing media library with the directory$")
    public void I_have_an_existing_media_library_with_the_directory() throws Throwable {
        mediaLibraryService.createMediaLibrary(mediaDirectory, "Pre-existing Media Library");
    }

    @Given("^the directory cannot be read$")
    public void the_directory_cannot_be_read() throws Throwable {
        mediaDirectory = Mockito.spy(mediaDirectory);
        Mockito.when(mediaDirectory.canRead()).thenReturn(false);
    }

    @Given("^the directory does not exist$")
    public void the_directory_does_not_exist() throws Throwable {
        mediaDirectory = Mockito.spy(mediaDirectory);
        Mockito.when(mediaDirectory.exists()).thenReturn(false);
    }

    /*
     * WHEN steps
     */

    @When("^I choose the directory as a media library named \"([^\"]*)\"$")
    public void I_choose_the_directory_as_a_media_library_named(String libraryName) throws Throwable {
        try {
            mediaLibraryService.createMediaLibrary(mediaDirectory, libraryName);
        } catch (Exception e) {
            exception = e;
        }
    }

    /*
     * THEN steps
     */

    @Then("^I have a media library named \"([^\"]*)\"$")
    public void I_have_a_media_library_named(String libraryName) throws Throwable {
        mediaLibrary = mediaLibraryService.readMediaLibrary(libraryName);
        assertEquals(libraryName, mediaLibrary.getName());
        assertNull(exception);
    }

    @Then("^the directory for media library \"([^\"]*)\" matches the directory$")
    public void the_directory_for_media_library_matches_the_directory(String libraryName) throws Throwable {
        mediaLibrary = mediaLibraryService.readMediaLibrary(libraryName);
        assertEquals(mediaDirectory, mediaLibrary.getBaseDirectory());
        assertNull(exception);
    }

    @Then("^I get a duplicate media library error$")
    public void I_get_a_duplicate_media_library_error() throws Throwable {
        assertNotNull(exception);
        assertTrue(exception instanceof DuplicateMediaLibraryException);
    }

    @Then("^I get a invalid directory media library error$")
    public void I_get_a_invalid_directory_media_library_error() throws Throwable {
        assertNotNull(exception);
        assertTrue(exception instanceof InvalidMediaLibraryDirectoryException);
    }

}
