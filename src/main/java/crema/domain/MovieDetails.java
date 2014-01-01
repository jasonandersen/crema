package crema.domain;

import java.util.Collection;

/**
 * Detailed information about a particular movie.
 * 
 * @author Jason Andersen (andersen.jason@gmail.com)
 */
public interface MovieDetails {

    Integer getReleaseYear();

    String getSynopsis();

    Collection<String> getGenres();
}
