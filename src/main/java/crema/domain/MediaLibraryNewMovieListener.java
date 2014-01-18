package crema.domain;

import crema.exception.CremaException;

/**
 * Allows other objects to act on movies that are added to a {@link MediaLibrary}.
 * 
 * @author Jason Andersen (andersen.jason@gmail.com)
 */
public interface MediaLibraryNewMovieListener {

    /**
     * Called whenever a new movie has been added to the Media Library.
     * @param movie
     * @throws CremaException 
     */
    void movieAdded(Movie movie) throws CremaException;

}
