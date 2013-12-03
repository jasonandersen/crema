package crema.test.mock;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import crema.service.PreferencesService;

/**
 * Test implementation of {@link PreferencesService} backed by an in-memory hashmap.
 * This implementation allows tests to read and write application preferences without
 * corrupting the user's actual preferences.
 * 
 * @author Jason Andersen (andersen.jason@gmail.com)
 */
@Primary
@Service
public class PreferencesServiceInMemoryImpl implements PreferencesService {

    private static Logger log = LoggerFactory.getLogger(PreferencesServiceInMemoryImpl.class);

    private final Map<String, String> preferences;

    /**
     * Constructor
     */
    public PreferencesServiceInMemoryImpl() {
        log.info("instantiating");
        preferences = new HashMap<String, String>();
    }

    /**
     * Clear out all the preferences set in this user node.
     */
    public void clearPreferences() {
        log.debug("clearing out preferences");
        preferences.clear();
    }

    /**
     * @see crema.service.PreferencesService#getString(java.lang.String)
     */
    public String getString(String key) {
        String value = preferences.get(key);
        log.debug("getting preference '{}' as '{}'", key, value);
        return value;
    }

    /**
     * @see crema.service.PreferencesService#putString(java.lang.String, java.lang.String)
     */
    public void putString(String key, String value) {
        log.info("putting preference '{}' as '{}'", key, value);
        preferences.put(key, value);
    }

}
