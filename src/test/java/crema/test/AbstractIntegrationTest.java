package crema.test;

import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

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

    private static Lock shutdownHookLock = new ReentrantLock();

    private static Boolean shutdownHookRegistered = false;

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
        shutdownHookLock.lock();
        try {
            if (!shutdownHookRegistered) {
                Runtime.getRuntime().addShutdownHook(new CremaDirectoryCleanupShutdownHook());
                shutdownHookRegistered = true;
            }
        } finally {
            shutdownHookLock.unlock();
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

    /**
     * Before each test, clear out the database.
     */
    @Before
    @After
    public void truncateDatabase() {
        truncator.truncate();
    }

    /**
     * Pings a URL for availability. Use this method to determine if an external web service is available
     * prior to testing.
     * @param url
     * @param timeout
     * @return true if the URL is available, false if it is not
     */
    protected boolean ping(String candidateUrl, int timeout) {
        log.debug("pinging {}", candidateUrl);
        String url = candidateUrl.replaceFirst("https", "http"); // Otherwise an exception may be thrown on invalid SSL certificates.
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setConnectTimeout(timeout);
            connection.setReadTimeout(timeout);
            int responseCode = connection.getResponseCode();
            log.debug("response {}", responseCode);
            return (200 <= responseCode && responseCode <= 399);
        } catch (IOException exception) {
            return false;
        }
    }

    private class CremaDirectoryCleanupShutdownHook extends Thread {
        @Override
        public void run() {
            log.debug("running crema directory cleanup shutdown hook");
            if (cremaDir.exists()) {
                FileUtils.deleteQuietly(cremaDir);
            }
        }
    }

}
