package crema.exception;

/**
 * Indicates that a search for a particular movie was unsuccessful.
 * 
 * @author Jason Andersen (andersen.jason@gmail.com)
 */
public class MovieNotFoundException extends CremaException {

    /**
     * @param message
     */
    public MovieNotFoundException(String message) {
        super(message);
    }

}
