package crema.view.pivot.domain;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.mockito.Mockito;

import crema.domain.Movie;

/**
 * Testing the {@link MovieView} class.
 * 
 * @author Jason Andersen (andersen.jason@gmail.com)
 */
public class MovieViewTest {

    private MovieView movieView;

    @Test
    public void test1_0GB() {
        Movie mock = mockMovie(1000000000L);
        movieView = new MovieView(mock);
        assertEquals("1.0GB", movieView.getFileSize());
    }

    /**
     * @param total size in bytes
     * @return a mocked movie that will return a specific size
     */
    private Movie mockMovie(long size) {
        Movie movie = Mockito.mock(Movie.class);
        Mockito.when(movie.getTotalSize()).thenReturn(size);
        return movie;
    }

}
