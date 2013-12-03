package crema.test;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import crema.exception.PreferencesException;
import crema.test.mock.PreferencesServiceInMemoryImpl;

/**
 * Base class for unit tests based on the spring context.
 * 
 * @author Jason Andersen (andersen.jason@gmail.com)
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/applicationContext.xml")
public abstract class AbstractSpringTest {

    @Autowired
    private PreferencesServiceInMemoryImpl testPreferencesService;

    /**
     * Clear out all preferences prior to executing a test.
     * @throws PreferencesException
     */
    @Before
    public void setupPreferencesForTest() throws PreferencesException {
        testPreferencesService.clearPreferences();
    }
}
