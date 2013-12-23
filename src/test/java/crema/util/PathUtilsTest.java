package crema.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.io.File;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import crema.test.TestUtils;

/**
 * Testing the {@link PathUtils} class.
 * 
 * @author Jason Andersen (andersen.jason@gmail.com)
 */
public class PathUtilsTest {

    private File directory;

    private File file;

    @Before
    public void setup() {
        directory = TestUtils.buildTestDirectory(getClass());
    }

    @Test
    public void testGetFileExtension() throws IOException {
        buildFile("monkey.txt");
        assertEquals("txt", PathUtils.getFileExtension(file));
    }

    @Test
    public void testNullFile() {
        File nullFile = null;
        assertNull(PathUtils.getFileExtension(nullFile));
    }

    @Test
    public void testNoExtension() throws IOException {
        buildFile("monkey");
        assertEquals("", PathUtils.getFileExtension(file));
    }

    /**
     * Build a file relative to the directory in this test.
     * @param fileName
     * @throws IOException
     */
    private void buildFile(final String fileName) throws IOException {
        file = TestUtils.buildFileRelativeToDirectory(directory, fileName);
    }
}
