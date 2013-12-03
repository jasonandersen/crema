package crema.exception;

import crema.domain.MediaLibrary;

/**
 * An exception relating to {@link MediaLibrary}s.
 * 
 * @author Jason Andersen (andersen.jason@gmail.com)
 */
public class MediaLibraryException extends AbstractCremaException {

    /**
     * Constructor
     * @param message
     */
    public MediaLibraryException(String message) {
        super(message);
    }

}
