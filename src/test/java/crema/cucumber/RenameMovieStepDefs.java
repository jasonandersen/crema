package crema.cucumber;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;

import crema.domain.MediaLibrary;
import crema.domain.Movie;
import crema.service.MediaLibraryService;
import crema.service.MovieService;
import crema.test.TestUtils;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

/**
 * Step definitions related to renaming a movie.
 * 
 * @author Jason Andersen (andersen.jason@gmail.com)
 */
public class RenameMovieStepDefs extends AbstractCucumberStepDefs {

    @Autowired
    private MediaLibraryService libraryService;

    @Autowired
    private MovieService movieService;

    private Movie movie;

    private MediaLibrary mediaLibrary;

    @Given("^I have a media library that contains a movie named \"([^\"]*)\"$")
    public void I_have_a_media_library_that_contains_a_movie_named(String movieName) throws Throwable {
        File directory = TestUtils.buildTestDirectory(getClass());
        String fileName = movieName;
        fileName += ".avi";
        TestUtils.buildFileRelativeToDirectory(directory, fileName);
        mediaLibrary = libraryService.createMediaLibrary(directory, "Test Media Library");
        movie = mediaLibrary.getMovies(movieName).iterator().next();
        assertNotNull(movie);
        assertEquals(movieName, movie.getName());
    }

    @When("^I rename a movie named \"([^\"]*)\" to \"([^\"]*)\"$")
    public void I_rename_a_movie_named_to(String originalName, String newName) throws Throwable {
        assertNotNull(movie);
        assertEquals(originalName, movie.getName());
        movie.setName(newName);
        movieService.updateMovie(movie);
    }

    @Then("^the movie's name is now \"([^\"]*)\"$")
    public void the_movie_s_name_is_now(String movieName) throws Throwable {
        Movie savedMovie = movieService.getMovie(movie.getId());
        assertSame(movie, savedMovie);
        assertEquals(movieName, savedMovie.getName());
    }

}
