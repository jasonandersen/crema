package crema.exception;

/**
 * An exception relating to MediaLibrarys.
 * 
 * @author Jason Andersen (andersen.jason@gmail.com)
 */
public class MediaLibraryException extends CremaException {

    /**
     * Constructor.
     * @param message
     */
    public MediaLibraryException(final String message) {
        super(message);
    }

    /**
     * Constructor.
     * @param message
     * @param e
     */
    public MediaLibraryException(final String message, final Throwable e) {
        super(message, e);
    }

}
