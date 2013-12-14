package crema.exception;

import crema.domain.MediaLibrary;

/**
 * An exception relating to {@link MediaLibrary}s.
 * 
 * @author Jason Andersen (andersen.jason@gmail.com)
 */
public class MediaLibraryException extends CremaException {

    /**
     * Constructor
     * @param message
     */
    public MediaLibraryException(String message) {
        super(message);
    }

    /**
     * Constructor
     * @param message
     * @param e
     */
    public MediaLibraryException(String message, Throwable e) {
        super(message, e);
    }

}
