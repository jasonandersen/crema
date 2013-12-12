package crema.exception;

/**
 * Indicates that a media library already exists.
 * 
 * @author Jason Andersen (andersen.jason@gmail.com)
 */
public class DuplicateMediaLibraryException extends CremaException {

    /**
     * Constructor
     * @param message
     */
    public DuplicateMediaLibraryException(String message) {
        super(message);
    }

}