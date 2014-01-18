package crema.service;

import crema.domain.Movie;
import crema.exception.CremaException;

/**
 * Builds out information about a movie.
 * 
 * @author Jason Andersen (andersen.jason@gmail.com)
 */
public interface MovieDecorator {

    /**
     * Decorates a single movie with more information.
     * @param movie
     * @throws CremaException 
     */
    void decorateMovie(Movie movie) throws CremaException;

}
