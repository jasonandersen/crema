package crema.exception;

/**
 * Indicates that a media library is not a valid directory.
 *
 * @author Jason Andersen (andersen.jason@gmail.com)
 */
public class InvalidMediaLibraryDirectoryException extends AbstractCremaException {

    /**
     * Constructor
     * @param message
     */
    public InvalidMediaLibraryDirectoryException(String message) {
        super(message);
    }
}
