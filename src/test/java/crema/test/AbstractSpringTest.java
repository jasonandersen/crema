package crema.test;

import java.io.File;

import javax.annotation.PostConstruct;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import crema.exception.PreferencesException;
import crema.service.CremaDirectoryService;
import crema.test.mock.PreferencesServiceInMemoryImpl;

/**
 * Base class for unit tests based on the spring context.
 * 
 * @author Jason Andersen (andersen.jason@gmail.com)
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/applicationContext.xml")
public abstract class AbstractSpringTest {

    private static Logger log = LoggerFactory.getLogger(AbstractSpringTest.class);

    private static File cremaDir;

    @Autowired
    private PreferencesServiceInMemoryImpl testPreferencesService;

    @Autowired
    private CremaDirectoryService cremaDirectory;

    @PostConstruct
    public void setupCremaDirectory() throws PreferencesException {
        cremaDir = cremaDirectory.getCremaDirectory();
    }

    /**
     * Wipe out the crema directory
     */
    @AfterClass
    public static void clearOutTempCremaDirectory() {
        for (File file : cremaDir.listFiles()) {
            log.debug("deleting temporary directory contents: {}", file);
            file.delete();
        }
    }

    /**
     * Clear out all preferences prior to executing a test.
     * @throws PreferencesException
     */
    @Before
    public void setupPreferencesForTest() throws PreferencesException {
        testPreferencesService.clearPreferences();
    }

}
