package crema.service;

import crema.domain.MediaLibraryNewMovieListener;

/**
 * Aggregates all the {@MovieDecorator} objects into one service.
 * 
 * @author Jason Andersen (andersen.jason@gmail.com)
 */
public interface MovieDecorationService extends MediaLibraryNewMovieListener {
    //TODO - this doesn't smell right
}
