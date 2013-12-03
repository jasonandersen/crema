package crema.dao.db4o;

import org.springframework.stereotype.Repository;

import crema.dao.MediaLibraryDAO;
import crema.domain.MediaLibrary;

/**
 * Implementation of the {@link MediaLibraryDAO} interface using db4o.
 * 
 * @author Jason Andersen (andersen.jason@gmail.com)
 */
@Repository
public class MediaLibraryDAOdb4oImpl implements MediaLibraryDAO {

    /**
     * @see crema.dao.MediaLibraryDAO#save(crema.domain.MediaLibrary)
     */
    public MediaLibrary save(MediaLibrary library) {
        return null;
    }

}
