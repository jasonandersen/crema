package crema.cucumber;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import crema.domain.MediaLibrary;
import crema.service.MediaLibraryService;
import crema.test.TestUtil;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

/**
 * Step definitions for Cucumber tests related to discovering movie files in a media library.
 * 
 * @author Jason Andersen (andersen.jason@gmail.com)
 */
public class DiscoverMovieFilesInLibraryStepDefs extends AbstractCucumberStepDefs {

    private static Logger log = LoggerFactory.getLogger(DiscoverMovieFilesInLibraryStepDefs.class);

    @Autowired
    private MediaLibraryService libraryService;

    private File directory;

    private File targetFile;

    private MediaLibrary mediaLibrary;

    @Before
    public void setupDirectory() {
        directory = TestUtil.buildTestMediaDirectory(getClass());
        directory.mkdirs();
        log.debug("setup test directory {}", directory);
    }

    @After
    public void tearDownFiles() throws IOException {
        if (targetFile != null) {
            targetFile = null;
        }
        if (directory != null) {
            deleteDirectory(directory);
            directory = null;
        }
    }

    /**
     * Clean up the database before and after each test
     */
    @Before
    @After
    public void cleanupDatabase() {
        truncateDatabase();
    }

    @Given("^I have a directory that contains a file named ([^\"]*)$")
    public void I_have_a_directory_that_contains_a_file_named(final String fileName) throws Exception {
        String path = directory.getPath();
        path += path.endsWith(File.separator) ? "" : File.separator;
        path += fileName;
        targetFile = new File(path);
        targetFile.createNewFile();
        log.debug("created test file {}", targetFile);
    }

    @When("^I add that directory as a new media library$")
    public void I_add_that_directory_as_a_new_media_library() throws Throwable {
        mediaLibrary = libraryService.createMediaLibrary(directory, "Test Media Library 1");
    }

    @Then("^([^\"]*) is ([^\"]*) to the media library$")
    public void file_avi_is_added_to_the_media_library(final String fileName, final String result) throws Throwable {
        boolean added = "added".equals(result);
        assertEquals(added, mediaLibrary.containsFile(fileName));
    }
}
