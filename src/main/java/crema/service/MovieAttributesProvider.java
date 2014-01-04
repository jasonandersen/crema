package crema.service;

import crema.domain.Movie;

/**
 * Provides {@link Attribute}s for {@link Movie}s, usually through some external service
 * like rottentomatoes.com or freebase.com.
 * 
 * @author Jason Andersen (andersen.jason@gmail.com)
 */
public interface MovieAttributesProvider {

    /**
     * Builds out attributes and injects them into the {@link Movie} object.
     * @param movie
     */
    void provideAttributes(Movie movie);

}
