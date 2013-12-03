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
    public MediaLibrary save(MediaLibrary library);

}
