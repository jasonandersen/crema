package crema.service;

import crema.domain.Movie;

/**
 * A service that guesses the name of a movie based on it's file.
 * 
 * @author Jason Andersen (andersen.jason@gmail.com)
 */
public interface MovieNameService {

    /**
     * Will make a best guess on the name of a movie based on the name
     * of the file.
     * @param movie
     */
    void guessName(Movie movie);
}
