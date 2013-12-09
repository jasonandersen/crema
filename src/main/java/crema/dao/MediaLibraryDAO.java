package crema.dao;

import crema.domain.MediaLibrary;

/**
 * Data access object for {@link MediaLibrary} objects.
 * 
 * @author Jason Andersen (andersen.jason@gmail.com)
 */
public interface MediaLibraryDAO {

    /**
     * Persist a media library
     * @param library
     * @return the instance of the library that's been persisted
     */
    public void save(MediaLibrary library);

    /**
     * @param name
     * @return the instance of the library with the specified name, will return null if the name is not found
     */
    public MediaLibrary read(String name);

}
