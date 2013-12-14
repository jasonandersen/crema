package crema.service;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.After;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import crema.exception.PreferencesException;
import crema.test.AbstractIntegrationTest;

/**
 * Testing that preferences get persisted.
 * 
 * @author Jason Andersen (andersen.jason@gmail.com)
 */
public class CremaDirectoryPreferencesPersistedTest extends AbstractIntegrationTest {

    private static final Logger log = LoggerFactory.getLogger(CremaDirectoryLocatorTest.class);

    @Autowired
    private CremaDirectoryLocator target;

    private File cremaDir;

    @After
    public void tearDown() {
        log.debug("deleting the testing directory: {}", cremaDir);
        cremaDir.delete();
    }

    @Test
    public void testDirectoryPreferencePersisted() throws PreferencesException {
        assertFalse(target.isCremaDirectorySpecified());
        cremaDir = target.getCremaDirectory();
        assertTrue(target.isCremaDirectorySpecified());
    }

}
