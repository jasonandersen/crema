package crema.exception;

/**
 * Base exception class for application exceptions.
 * 
 * @author Jason Andersen (andersen.jason@gmail.com)
 */
public abstract class CremaException extends Exception {

    /**
     * Constructor
     * @param message
     */
    public CremaException(String message) {
        super(message);
    }

    /**
     * Constructor
     * @param message
     * @param e
     */
    public CremaException(String message, Throwable e) {
        super(message, e);
    }
}
