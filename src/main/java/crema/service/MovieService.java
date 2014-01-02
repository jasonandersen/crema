package crema.service;

import java.util.UUID;

import crema.domain.Movie;
import crema.exception.DuplicateMovieException;
import crema.exception.MovieNotFoundException;

/**
 * Provides services related to {@link Movie} objects.
 * 
 * @author Jason Andersen (andersen.jason@gmail.com)
 */
public interface MovieService {

    /**
     * Persists any changes to a movie object.
     * @param movie
     * @throws DuplicateMovieException 
     */
    void updateMovie(Movie movie) throws DuplicateMovieException;

    /**
     * @param id
     * @return a movie with a matching UUID - will never return null
     * @throws MovieNotFoundException when no movie was found with a matching UUID
     */
    Movie getMovie(UUID id) throws MovieNotFoundException;

}
