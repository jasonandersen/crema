package crema.dao;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import crema.domain.MediaFile;
import crema.domain.MediaLibrary;
import crema.domain.Movie;
import crema.exception.DuplicateMovieException;
import crema.exception.MediaFileException;
import crema.exception.MovieNotFoundException;
import crema.test.AbstractIntegrationTest;
import crema.test.TestUtils;

/**
 * Testing the implementation of {@link MovieDAO}.
 * 
 * @author Jason Andersen (andersen.jason@gmail.com)
 */
public class MovieDAOTest extends AbstractIntegrationTest {

    @Autowired
    private MovieDAO movieDao;

    private MediaLibrary library;

    private Movie movie;

    private UUID uuid = UUID.randomUUID();

    @Before
    public void setup() throws MediaFileException, IOException {
        File directory = TestUtils.buildTestDirectory(getClass());
        File file = TestUtils.buildFileRelativeToDirectory(directory, "i-like-monkeys.avi");

        library = new MediaLibrary();
        library.setBaseDirectory(directory);

        movie = new Movie();
        movie.setId(uuid);
        movie.setName("I Like Monkeys");

        MediaFile mediaFile = new MediaFile(library, file);
        movie.addFile(mediaFile);
    }

    @Test
    public void testSave() throws DuplicateMovieException {
        movieDao.save(movie);
    }

    @Test
    public void testRead() throws MovieNotFoundException, DuplicateMovieException {
        movieDao.save(movie);
        Movie savedMovie = movieDao.read(uuid);
        assertNotNull(savedMovie);
        assertSame(savedMovie, movie);
    }

    @Ignore
    @Test(expected = DuplicateMovieException.class)
    public void testUUIDUniqueness() throws DuplicateMovieException {
        /*
         * TODO - for reasons I don't understand, I can't get unique indexing to work on the movie ID field
         */
        movieDao.save(movie);

        Movie duplicateMovie = new Movie();
        duplicateMovie.setName("This is a duplicate movie, should not be allowed to be saved.");
        duplicateMovie.setId(uuid);

        movieDao.save(duplicateMovie);
    }

}
