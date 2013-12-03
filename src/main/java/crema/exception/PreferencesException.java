package crema.exception;

/**
 * Indicates an exception with application preferences.
 * 
 * @author Jason Andersen (andersen.jason@gmail.com)
 */
public class PreferencesException extends AbstractCremaException {

    /**
     * Constructor
     * @param format
     */
    public PreferencesException(String message) {
        super(message);
    }
}
