package crema.exception;

/**
 * Base exception class for application exceptions.
 * 
 * @author Jason Andersen (andersen.jason@gmail.com)
 */
public abstract class CremaException extends Exception {

    /**
     * Constructor.
     * @param message
     */
    public CremaException(final String message) {
        super(message);
    }

    /**
     * Constructor.
     * @param message
     * @param e
     */
    public CremaException(final String message, final Throwable e) {
        super(message, e);
    }
}
