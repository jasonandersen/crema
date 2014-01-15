package crema.exception;

/**
 * Indicates an exception occurred while gathering attributes for a movie.
 * 
 * @author Jason Andersen (andersen.jason@gmail.com)
 */
public class AttributesProviderException extends CremaException {

    /**
     * Constructor.
     * @param message
     */
    public AttributesProviderException(final String message) {
        super(message);
    }

    /**
     * Constructor.
     * @param e
     */
    public AttributesProviderException(final Exception e) {
        super(e.getMessage(), e);
    }

}
