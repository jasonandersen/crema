package crema.test;

import java.io.File;
import java.io.IOException;

import javax.annotation.PostConstruct;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import crema.exception.PreferencesException;
import crema.service.CremaDirectoryLocator;
import crema.test.beans.DatabaseTruncator;
import crema.test.beans.PreferencesServiceInMemoryImpl;

/**
 * Base class for unit tests based on the spring context.
 * 
 * @author Jason Andersen (andersen.jason@gmail.com)
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/applicationContext.xml")
@DirtiesContext
public abstract class AbstractIntegrationTest {

    private static Logger log = LoggerFactory.getLogger(AbstractIntegrationTest.class);

    private File cremaDir;

    @Autowired
    private PreferencesServiceInMemoryImpl testPreferencesService;

    @Autowired
    private CremaDirectoryLocator cremaDirectory;

    @Autowired
    private DatabaseTruncator truncator;

    @PostConstruct
    public void setupCremaDirectory() throws PreferencesException {
        cremaDir = cremaDirectory.getCremaDirectory();
        //ensure the crema directory is shutdown after the JVM exits
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                try {
                    if (cremaDir.exists()) {
                        FileUtils.deleteDirectory(cremaDir);
                    }
                } catch (IOException e) {
                    log.error("failed to delete crema directory on shutdown hook", e);
                }
            }
        });
    }

    /**
     * Clear out all preferences prior to executing a test.
     * @throws PreferencesException
     */
    @Before
    public void setupPreferencesForTest() throws PreferencesException {
        testPreferencesService.clearPreferences();
    }

    /**
     * Before each test, clear out the database.
     */
    @Before
    @After
    public void truncateDatabase() {
        truncator.truncate();
    }

}
