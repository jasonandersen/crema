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
     * Constructor
     */
    public PreferencesImpl() {
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
