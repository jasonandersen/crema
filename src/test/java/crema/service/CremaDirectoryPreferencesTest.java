package crema.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;

import crema.exception.PreferencesException;
import crema.test.AbstractSpringTest;
import crema.test.MockingUtil;

/**
 * Testing the ability to specify a crema default directory
 * 
 * @author Jason Andersen (andersen.jason@gmail.com)
 */
@DirtiesContext
public class CremaDirectoryPreferencesTest extends AbstractSpringTest {

    private static final Logger log = LoggerFactory.getLogger(CremaDirectoryPreferencesTest.class);

    private static final String TEMP_DIR = System.getProperty("java.io.tmpdir");

    @Autowired
    private PreferencesService target;

    private File cremaDir;

    @Before
    public void setup() throws PreferencesException {
        //setup the mock OSDirectoryService
        log.debug("mocking OSDirectoryService to return the user home dir: {}", TEMP_DIR);
        PreferencesServiceImpl service = (PreferencesServiceImpl) target;
        OSDirectoryService mockOsDirService = MockingUtil.mockOSDirectoryService();
        service.setOSDirectoryService(mockOsDirService);
        if (target != null) {
            cremaDir = target.getCremaDirectory();
        }
    }

    @After
    public void tearDown() {
        log.debug("deleting the testing directory: {}", cremaDir);
        cremaDir.delete();
    }

    @Test
    public void testDependencyInjection() {
        assertNotNull(target);
    }

    @Test
    public void testCorrectDirectory() {
        String expectedPath = String.format("%s.crema", TEMP_DIR);
        assertEquals(expectedPath, cremaDir.getPath());
    }

    @Test
    public void testDirectoryNotNull() {
        assertNotNull(cremaDir);
    }

    @Test
    public void testIsDirectory() {
        assertTrue(cremaDir.isDirectory());
    }

    @Test
    public void testDirectoryExists() {
        assertTrue(cremaDir.exists());
    }

    @Test
    public void testCanRead() {
        assertTrue(cremaDir.canRead());
    }

    @Test
    public void testCanWrite() {
        assertTrue(cremaDir.canWrite());
    }

}
