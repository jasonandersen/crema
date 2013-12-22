package crema.cucumber;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import crema.domain.MediaLibrary;
import crema.service.MediaLibraryService;
import crema.test.TestUtils;
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

    private MediaLibrary mediaLibrary;

    /*
     * Test setup and teardown
     */

    @Before
    public void setupDirectory() {
        directory = TestUtils.buildTestMediaDirectory(getClass());
        directory.mkdirs();
        log.debug("setup test directory {}", directory);
    }

    @After
    public void tearDownFiles() throws IOException {
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

    /*
     * GIVEN
     */

    @Given("^I have a directory that contains a file named ([^\"]*)$")
    public void I_have_a_directory_that_contains_a_file_named(final String fileName) throws Exception {
        createFileInDirectory(fileName);
    }

    @Given("^I have a directory that contains these files:$")
    public void I_have_a_directory_that_contains_these_files(final List<String> fileNames) throws Throwable {
        for (String fileName : fileNames) {
            createFileInDirectory(fileName);
        }
    }

    /*
     * WHEN
     */

    @When("^I add that directory as a new media library$")
    public void I_add_that_directory_as_a_new_media_library() throws Throwable {
        mediaLibrary = libraryService.createMediaLibrary(directory, "Test Media Library 1");
    }

    /*
     * THEN
     */

    @Then("^([^\"]*) is ([^\"]*) to the media library$")
    public void file_avi_is_added_to_the_media_library(final String fileName, final String result) throws Throwable {
        boolean expected = "added".equals(result);
        boolean actual = mediaLibrary.containsFile(fileName);
        assertEquals(expected, actual);
    }

    @Then("^these files have been added to the media library:$")
    public void these_files_have_been_added_to_the_media_library(final List<String> fileNames) throws Throwable {
        for (String fileName : fileNames) {
            assertTrue(mediaLibrary.containsFile(fileName));
        }
    }

    @Then("^these files have not been added to the media library:$")
    public void these_files_have_not_been_added_to_the_media_library(List<String> fileNames) throws Throwable {
        for (String fileName : fileNames) {
            assertFalse(mediaLibrary.containsFile(fileName));
        }
    }

    /**
     * Creates a single file in the directory
     * @param fileName name of the file, can include a relative path of subdirectories to build out the file in
     * @throws IOException
     */
    private void createFileInDirectory(String fileName) throws IOException {
        String path = directory.getPath();
        path += path.endsWith(File.separator) ? "" : File.separator;
        String relativePath = fileName.replace("/", File.separator);
        path += relativePath;
        File file = new File(path);
        FileUtils.touch(file);
        log.debug("test file created: {}", file);
    }
}
