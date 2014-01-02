package crema.exception;

/**
 * Indicates that a duplicate movie was discovered.
 * 
 * @author Jason Andersen (andersen.jason@gmail.com)
 */
public class DuplicateMovieException extends CremaException {

    /**
     * @param message
     */
    public DuplicateMovieException(final String message) {
        super(message);
    }

    /**
     * @param message
     * @param e
     */
    public DuplicateMovieException(final String message, final Throwable e) {
        super(message, e);
    }

}
