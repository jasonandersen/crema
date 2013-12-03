package crema.service;

/**
 * Provides application preferences
 * 
 * @author Jason Andersen (andersen.jason@gmail.com)
 */
public interface PreferencesService {

    /**
     * Directory used to store application data
     */
    public static final String KEY_CREMA_DIRECTORY = "crema.dir";

    /**
     * @param key
     * @return application preference stored at that key, will return null if not found
     */
    public String getString(String key);

    /**
     * Persists an application preference value at the specified key
     * @param key
     * @param value
     */
    public void putString(String key, String value);
}
