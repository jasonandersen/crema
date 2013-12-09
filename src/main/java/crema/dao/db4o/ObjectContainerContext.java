package crema.dao.db4o;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.config.EmbeddedConfiguration;
import com.db4o.constraints.UniqueFieldValueConstraint;
import com.db4o.query.Predicate;

import crema.domain.MediaLibrary;

/**
 * Manages the db4o {@link ObjectContainer} life cycle.
 * 
 * @author Jason Andersen (andersen.jason@gmail.com)
 */
@Component
public class ObjectContainerContext {

    private static Logger log = LoggerFactory.getLogger(ObjectContainerContext.class);

    @Autowired
    private DatabaseFile dbFile;

    /**
     * Initializes the container. DO NOT CALL THIS METHOD. This will be called by the
     * framework that manages the life cycle of this class.
     * @throws IOException 
     */
    @PostConstruct
    public void initializeContainer() throws IOException {
        log.info("initializing");
        configureContainer();
    }

    /**
     * Stores an object in the object database
     * @param object
     */
    public void store(Object object) {
        ObjectContainer container = getContainer();
        try {
            container.store(object);
            container.commit();
        } finally {
            container.close();
        }
    }

    /**
     * Queries the database.
     * @param predicate
     * @return a list of objects specified by the predicate
     */
    public <T> List<T> query(Predicate<T> predicate) {
        ObjectContainer container = getContainer();
        try {
            List<T> results = container.query(predicate);
            return new ArrayList<T>(results);
        } finally {
            container.close();
        }
    }

    /**
     * @return a newly instantiated ObjectContainer
     */
    private ObjectContainer getContainer() {
        log.debug("instantiating new ObjectContainer");
        return Db4oEmbedded.openFile(dbFile.getPath());
    }

    /**
     * Configure the db4o database
     */
    private void configureContainer() {
        log.info("configure db4o");
        EmbeddedConfiguration config = Db4oEmbedded.newConfiguration();
        //MediaLibrary indexing
        config.common().objectClass(MediaLibrary.class).objectField("name").indexed(true);
        config.common().add(new UniqueFieldValueConstraint(MediaLibrary.class, "name"));
    }

}
