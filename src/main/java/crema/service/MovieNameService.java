package crema.service;

import crema.domain.MediaLibraryNewMovieListener;
import crema.domain.Movie;

/**
 * A service that derives the name of a movie based on it's file.
 * 
 * @author Jason Andersen (andersen.jason@gmail.com)
 */
@Deprecated
public interface MovieNameService extends MediaLibraryNewMovieListener {

    /**
     * Will derive the name of a movie based on the name of the file.
     * @param movie
     */
    void deriveName(Movie movie);
}
