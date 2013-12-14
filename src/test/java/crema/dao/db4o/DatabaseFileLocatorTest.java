package crema.dao.db4o;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import crema.test.AbstractIntegrationTest;

/**
 * Hits the {@link DatabaseFileLocator} class with a brick.
 * 
 * @author Jason Andersen (andersen.jason@gmail.com)
 */
public class DatabaseFileLocatorTest extends AbstractIntegrationTest {

    @Autowired
    private DatabaseFileLocator target;

    @Autowired
    @Qualifier("defaultDatabaseFile")
    private String dbFileName;

    @Test
    public void testDependencyInjection() {
        assertNotNull(target);
    }

    @Test
    public void testDatabaseFilePath() {
        String path = target.getPath();
        assertNotNull(path);
        assertFalse(path.isEmpty());
    }

    @Test
    public void testDatabaseFileName() {
        assertTrue(target.getPath().endsWith(dbFileName));
    }

    @Test
    public void testFileExists() {
        File file = new File(target.getPath());
        assertTrue(file.exists());
    }
}
