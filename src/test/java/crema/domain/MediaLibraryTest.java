package crema.domain;

import static org.junit.Assert.assertEquals;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

/**
 * Testing the {@link MediaLibrary} class.
 * 
 * @author Jason Andersen (andersen.jason@gmail.com)
 */
public class MediaLibraryTest {

    private MediaLibrary library;

    private Map<String, Movie> movies;

    @Before
    public void setup() {
        library = new MediaLibrary();
        movies = new HashMap<String, Movie>();
        library.setMovies(movies);
    }

    @Test
    public void testGetMovie() {
        movies.put("blah.av", createMovie("Blah"));
        movies.put("blah2.avi", createMovie("Blah 2"));
        Collection<Movie> result = library.getMovies("Blah");
        assertEquals(1, result.size());
        Movie movie = result.iterator().next();
        assertEquals("Blah", movie.getName());
    }

    /**
     * @param name
     * @return test movie instance
     */
    private Movie createMovie(String name) {
        Movie movie = new Movie();
        movie.setName(name);
        return movie;
    }

}
