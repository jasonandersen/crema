package crema.exception;

/**
 * Indicates that a media library already exists.
 * 
 * @author Jason Andersen (andersen.jason@gmail.com)
 */
public class DuplicateMediaLibraryException extends MediaLibraryException {

    /**
     * Constructor.
     * @param message
     */
    public DuplicateMediaLibraryException(final String message) {
        super(message);
    }

    /**
     * Constructor.
     * @param message
     * @param e
     */
    public DuplicateMediaLibraryException(final String message, final Throwable e) {
        super(message, e);
    }

}
