package crema.exception;

/**
 * Indicates an exception with application preferences.
 * 
 * @author Jason Andersen (andersen.jason@gmail.com)
 */
public class PreferencesException extends CremaException {

    /**
     * Constructor
     * @param format
     */
    public PreferencesException(String message) {
        super(message);
    }

    /**
     * Constructor
     * @param message
     * @param e
     */
    public PreferencesException(String message, Throwable e) {
        super(message, e);
    }
}
