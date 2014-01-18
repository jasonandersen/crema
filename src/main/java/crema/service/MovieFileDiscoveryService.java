package crema.service;

import crema.domain.MediaLibrary;
import crema.exception.CremaException;
import crema.exception.MediaFileException;

/**
 * Discovers movies files within a {@link MediaLibrary}.
 * 
 * @author Jason Andersen (andersen.jason@gmail.com)
 */
public interface MovieFileDiscoveryService {

    /**
     * Discovers any movies files that exist within this MediaLibrary.
     * @param library
     * @throws MediaFileException 
     * @throws CremaException 
     */
    void discoverMovies(final MediaLibrary library) throws MediaFileException, CremaException;
}
