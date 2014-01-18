package crema.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import crema.domain.Movie;
import crema.exception.CremaException;
import crema.service.MovieDecorator;
import crema.util.text.MovieNameTokenizer;

/**
 * Decorates a movie by deriving the name of the movie from the file names
 * that make up the movie.
 * 
 * @author Jason Andersen (andersen.jason@gmail.com)
 */
@Service
public class MovieNameDecorator implements MovieDecorator {

    private static Logger log = LoggerFactory.getLogger(MovieNameDecorator.class);

    @Autowired
    private MovieNameTokenizer tokenizer;

    /**
     * @see crema.service.MovieDecorator#decorateMovie(crema.domain.Movie)
     */
    public void decorateMovie(final Movie movie) throws CremaException {
        deriveName(movie);
    }

    /**
     * @see crema.service.MovieNameService#deriveName(crema.domain.Movie)
     */
    private void deriveName(final Movie movie) {
        String fileName = movie.getFirstMediaFile().getFileNameWithoutExtension();
        List<String> tokens = tokenizer.tokenize(fileName);
        String movieName = assembleTokens(tokens);
        movie.setName(movieName);
        log.debug("file: {} movie: {}", fileName, movieName);
    }

    /**
     * Assembles the tokens separated by a single space each.
     * @param tokens
     * @return the assembled string
     */
    private String assembleTokens(final List<String> tokens) {
        StringBuilder out = new StringBuilder();
        for (String token : tokens) {
            out.append(token).append(" ");
        }
        return out.toString().trim();
    }

}
