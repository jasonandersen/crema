package crema.dao.db4o;

import java.io.IOException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

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
public class ObjectContainerContextImpl implements ObjectContainerContext {

    private static Logger log = LoggerFactory.getLogger(ObjectContainerContextImpl.class);

    @Autowired
    private DatabaseFileLocator dbFileLocator;

    private ObjectContainer container;

    /**
     * @see crema.dao.db4o.ObjectContainerContext#store(java.lang.Object)
     */
    public void store(Object object) {
        getObjectContainer().store(object);
        getObjectContainer().commit();
    }

    /**
     * @see crema.dao.db4o.ObjectContainerContext#query(com.db4o.query.Predicate)
     */
    public <T> List<T> query(Predicate<T> predicate) {
        return getObjectContainer().query(predicate);
    }

    /**
     * @see crema.dao.db4o.ObjectContainerContext#rollBack()
     */
    public void rollBack() {
        container.rollback();
    }

    /**
     * @return the {@link ObjectContainer} for the db4o database.
     */
    public ObjectContainer getObjectContainer() {
        if (container.ext().isClosed()) {
            /*
             * if the container has been closed - try to recover before failing
             */
            initializeContainer();
        }
        return container;
    }

    /**
     * Initializes the container. DO NOT CALL THIS METHOD. This will be called by the
     * framework that manages the life cycle of this class.
     * @throws IOException 
     */
    @PostConstruct
    public void initializeContainer() {
        log.info("initializing");
        EmbeddedConfiguration config = configureContainer();
        container = Db4oEmbedded.openFile(config, dbFileLocator.getPath());
    }

    /**
     * Cleans up the ObjectContainer. DO NOT CALL THIS METHOD. This will be called by the
     * framework that manages the life cycle of this class.
     */
    @PreDestroy
    public void cleanupContainer() {
        log.warn("closing object container");
        if (getObjectContainer() != null) {
            while (!getObjectContainer().close()) {
                //keep calling close until the last session is closed
            }
        }
    }

    /**
     * Configure the db4o database
     */
    private EmbeddedConfiguration configureContainer() {
        log.info("configure db4o");
        EmbeddedConfiguration config = Db4oEmbedded.newConfiguration();
        //MediaLibrary indexing
        config.common().objectClass(MediaLibrary.class).objectField(MediaLibrary.FIELD_NAME).indexed(true);
        config.common().add(new UniqueFieldValueConstraint(MediaLibrary.class, MediaLibrary.FIELD_NAME));
        config.common().objectClass(MediaLibrary.class).objectField(MediaLibrary.FIELD_BASE_DIR).indexed(true);
        config.common().add(new UniqueFieldValueConstraint(MediaLibrary.class, MediaLibrary.FIELD_BASE_DIR));

        return config;
    }

}
