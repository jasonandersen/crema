package crema.dao.db4o;

import java.util.List;

import com.db4o.ObjectContainer;
import com.db4o.query.Predicate;

/**
 * Manages the db4o {@link ObjectContainer} life cycle.
 * 
 * @author Jason Andersen (andersen.jason@gmail.com)
 */
public interface ObjectContainerContext {

    /**
     * Stores an object in the object database.
     * @param object
     */
    void store(Object object);

    /**
     * Queries the database.
     * @param predicate
     * @return a list of objects specified by the predicate
     */
    <T> List<T> query(Predicate<T> predicate);

    /**
     * @return the db4o {@link ObjectContainer} instance
     */
    ObjectContainer getObjectContainer();

    /**
     * Rolls back a transaction.
     */
    void rollBack();

}
