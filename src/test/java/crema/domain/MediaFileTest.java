package crema.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import crema.exception.MediaFileException;
import crema.test.TestUtils;

/**
 * Test the constructor of a {@link MediaFile} will work properly.
 * 
 * @author Jason Andersen (andersen.jason@gmail.com)
 */
public class MediaFileTest {

    private static Logger log = LoggerFactory.getLogger(MediaFileTest.class);

    private final static String RELATIVE_PATH = "subdir1/subdir2/subdir3/movie.mpg";

    private MediaLibrary library;

    private File directory;

    private File file;

    private MediaFile mediaFile;

    @Before
    public void setup() throws IOException, MediaFileException {
        directory = TestUtils.buildTestMediaDirectory(getClass());
        file = TestUtils.buildFileRelativeToDirectory(directory, RELATIVE_PATH);
        library = new MediaLibrary();
        library.setBaseDirectory(directory);
        library.setName("Test Library");
        mediaFile = new MediaFile(library, file);
    }

    @After
    public void tearDownDirectory() throws IOException {
        FileUtils.deleteDirectory(directory);
    }

    @Test
    public void testConstructor() {
        assertNotNull(mediaFile.getLibrary());
        assertEquals(RELATIVE_PATH, mediaFile.getRelativePath());
    }

    @Test
    public void testFileName() {
        assertEquals("movie.mpg", mediaFile.getFileName());
    }

    @Test
    public void testToString() {
        log.info(mediaFile.toString());
    }
}
