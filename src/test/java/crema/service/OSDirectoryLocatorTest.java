package crema.service;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import crema.test.AbstractIntegrationTest;

/**
 * Tests the OSDirectoryService implementation.
 * 
 * @author Jason Andersen (andersen.jason@gmail.com)
 */
public class OSDirectoryLocatorTest extends AbstractIntegrationTest {

    private static final String ENV_VAR = "%userprofile%";

    @Autowired
    private OSDirectoryLocator target;

    private File userHomeDir;

    @Before
    public void setup() {
        if (target != null) {
            userHomeDir = new File(target.getUserHomeDirectoryPath());
        }
    }

    @Test
    public void testDependencyInjection() {
        assertNotNull(target);
    }

    @Test
    public void testDirectoryNotNull() {
        assertNotNull(target.getUserHomeDirectoryPath());
    }

    @Test
    public void testDirectoryExists() {
        assertTrue(userHomeDir.exists());
    }

    @Test
    public void testCanRead() {
        assertTrue(userHomeDir.canRead());
    }

    @Test
    public void testCanWrite() {
        assertTrue(userHomeDir.canWrite());
    }

    /**
     * According to this StackOverflow discussion, Java on certain Windows machines will
     * return the user home directory with an environment variable contained within the 
     * property value instead of being resolved out to an actual pass:
     * http://stackoverflow.com/questions/2134338/java-user-home-is-being-set-to-userprofile-and-not-being-resolved
     */
    @Test
    public void testWindowsJVMDefectUserHomeDirContainsEnvVariable() {
        String path = userHomeDir.getAbsolutePath();
        assertFalse(path.toLowerCase().contains(ENV_VAR));
    }

}
