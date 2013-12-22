package crema.service;

import crema.domain.MediaLibrary;
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
     */
    void discoverMovies(final MediaLibrary library) throws MediaFileException;
}
