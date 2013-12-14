package crema.service.impl;

import java.util.prefs.Preferences;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Implementation of {@link Preferences} using the Java Preferences API.
 * 
 * @author Jason Andersen (andersen.jason@gmail.com)
 */
@Service
public class PreferencesImpl implements crema.service.Preferences {

    private static Logger log = LoggerFactory.getLogger(PreferencesImpl.class);

    protected Preferences preferences;

    /**
     * Constructor - use this constructor!
     */
    public PreferencesImpl() {
        this(PreferencesImpl.class);
    }

    /**
     * Protected constructor for testing purposes to allow tests to define a different class
     * node to write preferences to so as not to interfere with existing preferences.
     * @param nodeClass
     */
    protected PreferencesImpl(Class<?> nodeClass) {
        log.info("instantiating user preferences for class {}", getClass());
        preferences = Preferences.userNodeForPackage(getClass());
    }

    /**
     * @see crema.service.Preferences#getString(java.lang.String)
     */
    public String getString(String key) {
        return preferences.get(key, null);
    }

    /**
     * @see crema.service.Preferences#putString(java.lang.String, java.lang.String)
     */
    public void putString(String key, String value) {
        log.info("putting preference: key {} value {}", key, value);
        preferences.put(key, value);
    }

}
