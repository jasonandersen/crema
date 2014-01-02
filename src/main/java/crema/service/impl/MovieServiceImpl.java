package crema.service.impl;

import java.util.UUID;

import org.apache.commons.lang.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import crema.dao.MovieDAO;
import crema.domain.Movie;
import crema.exception.DuplicateMovieException;
import crema.exception.MovieNotFoundException;
import crema.service.MovieService;

/**
 * Implementation of {@link MovieService}.
 * 
 * @author Jason Andersen (andersen.jason@gmail.com)
 */
@Service
public class MovieServiceImpl implements MovieService {

    private static Logger log = LoggerFactory.getLogger(MovieServiceImpl.class);

    @Autowired
    private MovieDAO movieDao;

    /**
     * @throws DuplicateMovieException 
     * @see crema.service.MovieService#updateMovie(crema.domain.Movie)
     */
    public void updateMovie(final Movie movie) throws DuplicateMovieException {
        log.debug("updating movie: {}", movie);
        Validate.notNull(movie);
        movieDao.save(movie);
    }

    /**
     * @see crema.service.MovieService#getMovie(java.util.UUID)
     */
    public Movie getMovie(final UUID id) throws MovieNotFoundException {
        log.debug("fetching movie: {}", id);
        return movieDao.read(id);
    }

}
