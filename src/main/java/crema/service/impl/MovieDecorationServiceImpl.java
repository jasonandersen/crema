package crema.service.impl;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import crema.domain.Movie;
import crema.exception.CremaException;
import crema.service.MovieDecorationService;
import crema.service.MovieDecorator;
import crema.web.rottentomatoes.RottenTomatoesMovieAttributesProvider;

/**
 * Aggregates all the {@link MovieDecorator} objects and executes them in order
 * against each new movie added.
 * 
 * @author Jason Andersen (andersen.jason@gmail.com)
 */
@Service
public class MovieDecorationServiceImpl implements MovieDecorationService {

    private final List<MovieDecorator> decorators;

    /**
     * Constructor.
     * @param movieNameDecorator
     * @param fileNameAttributeDecorator
     * @param rottenTomatoesDecorator
     */
    @Autowired
    public MovieDecorationServiceImpl(
            final MovieNameDecorator movieNameDecorator,
            final FileNameMovieAttributesProvider fileNameAttributeDecorator,
            final RottenTomatoesMovieAttributesProvider rottenTomatoesDecorator) {

        decorators = new LinkedList<MovieDecorator>();
        decorators.add(movieNameDecorator);
        decorators.add(fileNameAttributeDecorator);
        //decorators.add(rottenTomatoesDecorator); //<-- not quite ready for RottenTomatoes.com attributes provider!
    }

    /**
     * @throws CremaException 
     * @see crema.domain.MediaLibraryNewMovieListener#movieAdded(crema.domain.Movie)
     */
    public void movieAdded(final Movie movie) throws CremaException {
        if (movie == null) {
            return;
        }
        for (MovieDecorator decorator : decorators) {
            decorator.decorateMovie(movie);
        }
    }

}
