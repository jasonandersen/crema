package crema.service.impl;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import crema.service.Preferences;

/**
 * Testing the actual implementation of the {@link Preferences} service to ensure
 * that it works on each platform.
 * 
 * @author Jason Andersen (andersen.jason@gmail.com)
 */
public class PreferencesJavaPrefsAPIImplTest {

    private Preferences prefs;

    @Before
    public void setup() {
        prefs = new PreferencesImpl(this.getClass());
    }

    @Test
    public void testWritingPrefs() {
        prefs.putString("key", "monkey");
    }

    @Test
    public void testReadingPrefs() {
        String value = "monkey 2";
        prefs.putString("key2", value);
        assertEquals(value, prefs.getString("key2"));
    }
}
