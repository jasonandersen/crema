package crema.dao.db4o;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import crema.dao.MediaLibraryDAO;
import crema.domain.MediaLibrary;

/**
 * Implementation of the {@link MediaLibraryDAO} interface using db4o.
 * 
 * @author Jason Andersen (andersen.jason@gmail.com)
 */
@Repository
public class MediaLibraryDAOImpl implements MediaLibraryDAO {

    private static Logger log = LoggerFactory.getLogger(MediaLibraryDAO.class);

    @Autowired
    private ObjectContainerContext containerContext;

    /**
     * @see crema.dao.MediaLibraryDAO#save(crema.domain.MediaLibrary)
     */
    public void save(MediaLibrary library) {
        log.debug("saving MediaLibrary: {}", library);
        containerContext.store(library);
    }

    /**
     * @see crema.dao.MediaLibraryDAO#read(java.lang.String)
     */
    public MediaLibrary read(final String name) {
        /*
         * NOTE - I originally tried to use a native query here but ended up getting a strange
         * Db4oException. For reasons I don't understand, query-by-example works.
         */
        log.debug("reading MediaLibrary named  {}", name);
        MediaLibrary example = new MediaLibrary();
        example.setName(name);
        List<MediaLibrary> results = containerContext.getObjectContainer().queryByExample(example);
        log.debug("{} results found for MediaLibrary named {}", results.size(), name);
        return results.isEmpty() ? null : results.get(0);
    }
}