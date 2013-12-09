package crema.dao;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import crema.dao.db4o.DatabaseFile;
import crema.test.AbstractSpringTest;

/**
 * Testing the {@link DatabaseFile} class.
 * 
 * @author Jason Andersen (andersen.jason@gmail.com)
 */
public class DatabaseFileTest extends AbstractSpringTest {

    @Autowired
    private DatabaseFile dbFile;

    @Test
    public void testInjection() {
        assertNotNull(dbFile);
    }

    @Test
    public void testFile() {
        String filePath = dbFile.getPath();
        assertNotNull(filePath);
    }

    @Test
    public void testFileExists() {
        String filePath = dbFile.getPath();
        File file = new File(filePath);
        assertTrue(file.exists());
        assertTrue(file.canRead());
        assertTrue(file.canWrite());
        assertTrue(file.isFile());
    }

}
