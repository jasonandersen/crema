package crema.dao;

import java.util.List;

import crema.domain.MediaLibrary;
import crema.exception.DuplicateMediaLibraryException;

/**
 * Data access object for {@link MediaLibrary} objects.
 * 
 * @author Jason Andersen (andersen.jason@gmail.com)
 */
public interface MediaLibraryDAO {

    /**
     * Persist a media library.
     * @param library
     * @return the instance of the library that's been persisted
     * @throws DuplicateMediaLibraryException 
     */
    void save(MediaLibrary library) throws DuplicateMediaLibraryException;

    /**
     * @param name
     * @return the instance of the library with the specified name, will return null if the name is not found
     */
    MediaLibrary read(String name);

    /**
     * @return all media libraries that have been persisted
     */
    List<MediaLibrary> readAll();

}
