package crema.dao.db4o;

import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.db4o.constraints.UniqueFieldValueConstraintViolationException;

import crema.dao.MovieDAO;
import crema.domain.Movie;
import crema.exception.DuplicateMovieException;
import crema.exception.MovieNotFoundException;

/**
 * Implementation of {@link MovieDAO}.
 * 
 * @author Jason Andersen (andersen.jason@gmail.com)
 */
@Repository
public class MovieDAOImpl implements MovieDAO {

    private static Logger log = LoggerFactory.getLogger(MovieDAOImpl.class);

    @Autowired
    private ObjectContainerContext containerContext;

    /**
     * @throws DuplicateMovieException 
     * @see crema.dao.MovieDAO#save(crema.domain.Movie)
     */
    public void save(final Movie movie) throws DuplicateMovieException {
        log.debug("saving {}", movie);
        try {
            containerContext.store(movie);
        } catch (UniqueFieldValueConstraintViolationException e) {
            throw new DuplicateMovieException(e.getMessage(), e);
        }
    }

    /**
     * @see crema.dao.MovieDAO#read(java.util.UUID)
     */
    public Movie read(final UUID id) throws MovieNotFoundException {
        Movie example = new Movie();
        example.setId(id);
        List<Movie> results = containerContext.getObjectContainer().queryByExample(example);
        if (results.isEmpty()) {
            throw new MovieNotFoundException("no movie was found with an ID of " + id);
        }
        return results.get(0);
    }

}
