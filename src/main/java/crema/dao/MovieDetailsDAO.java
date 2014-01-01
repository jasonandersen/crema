package crema.dao;

import crema.domain.Movie;
import crema.domain.MovieDetails;

/**
 * Provides detailed information on individual movies.
 * 
 * @author Jason Andersen (andersen.jason@gmail.com)
 */
public interface MovieDetailsDAO {

    /**
     * @param movie
     * @return details about a given movie.
     */
    MovieDetails getMovieDetails(Movie movie);
}
