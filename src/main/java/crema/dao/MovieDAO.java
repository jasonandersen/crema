package crema.dao;

import java.util.UUID;

import crema.domain.Movie;
import crema.exception.DuplicateMovieException;
import crema.exception.MovieNotFoundException;

/**
 * Provides persistence for {@link Movie} objects.
 * 
 * @author Jason Andersen (andersen.jason@gmail.com)
 */
public interface MovieDAO {

    /**
     * Persists a movie to the database.
     * @param movie
     * @throws DuplicateMovieException 
     */
    void save(Movie movie) throws DuplicateMovieException;

    /**
     * @param id
     * @return the movie with a matching UUID, will not return null
     * @throws MovieNotFoundException when no matching movie was found
     */
    Movie read(UUID id) throws MovieNotFoundException;

}
