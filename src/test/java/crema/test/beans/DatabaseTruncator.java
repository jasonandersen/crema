package crema.test.beans;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.db4o.ObjectContainer;

import crema.dao.db4o.ObjectContainerContext;
import crema.domain.MediaLibrary;

/**
 * A service to truncate data from the database. Used to clear out data between tests.
 * 
 * @author Jason Andersen (andersen.jason@gmail.com)
 */
@Service
public class DatabaseTruncator {

    private static Logger log = LoggerFactory.getLogger(DatabaseTruncator.class);

    @Autowired
    private ObjectContainerContext containerContext;

    /**
     * Truncates all objects in the database. USE WITH CAUTION - THERE IS NO ROLLBACK!
     */
    public void truncate() {
        log.warn("truncating object container!");
        truncateClass(MediaLibrary.class);
        getObjectContainer().commit();
    }

    /**
     * Truncate a single class from the database
     */
    private <T> void truncateClass(Class<T> theClass) {
        List<T> results = getObjectContainer().query(theClass);
        for (T object : results) {
            getObjectContainer().delete(object);
        }
    }

    /**
     * @return the db4o object container
     */
    private ObjectContainer getObjectContainer() {
        return containerContext.getObjectContainer();
    }

}
