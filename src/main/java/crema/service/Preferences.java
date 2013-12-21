package crema.service;

/**
 * Provides application preferences.
 * 
 * @author Jason Andersen (andersen.jason@gmail.com)
 */
public interface Preferences {

    /**
     * Directory used to store application data.
     */
    String KEY_CREMA_DIRECTORY = "crema.dir";
    /**
     * Crema database file.
     */
    String KEY_CREMA_DB_FILE = "crema.db.file";

    /**
     * @param key
     * @return application preference stored at that key, will return null if not found
     */
    String getString(String key);

    /**
     * Persists an application preference value at the specified key.
     * @param key
     * @param value
     */
    void putString(String key, String value);
}
