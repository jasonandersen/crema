package crema.view.pivot.domain;

import org.apache.commons.lang.Validate;

import crema.domain.Movie;

/**
 * A view wrapper around a {@link Movie} object.
 * 
 * @author Jason Andersen (andersen.jason@gmail.com)
 */
public class MovieView {

    private final Movie movie;

    /**
     * Constructor.
     * @param movie
     */
    public MovieView(final Movie movie) {
        Validate.notNull(movie);
        this.movie = movie;
    }

    /**
     * @return name of the movie
     */
    public String getName() {
        return movie.getName();
    }

    /**
     * @return path of the movie file relative to the MediaLibrary
     */
    public String getRelativeFilePath() {
        return movie.getMediaFile().getRelativePath();
    }

    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return movie.getName();
    }
}
