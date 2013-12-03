package crema.exception;

/**
 * Base exception class for application exceptions.
 * 
 * @author Jason Andersen (andersen.jason@gmail.com)
 */
public abstract class AbstractCremaException extends Exception {

    /**
     * Constructor
     * @param message
     */
    public AbstractCremaException(String message) {
        super(message);
    }
}
