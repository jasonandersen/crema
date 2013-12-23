package crema.domain;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;

import crema.exception.MediaFileException;
import crema.test.TestUtils;

/**
 * Tests the containsFile() method of the {@link MediaLibrary} class.
 * 
 * @author Jason Andersen (andersen.jason@gmail.com)
 */
public class MediaLibraryContainsFileTest {

    private MediaLibrary library;

    private File baseDirectory;

    private Collection<File> files = new ArrayList<File>();

    @Before
    public void setupLibrary() throws MediaFileException, IOException {
        String[] paths = {
                "subdir1/subdir2/movie.avi",
                "subdir1/subdir2/movie.mpg",
                "subdir1/subdir2/movie.wmv",
                "subdir1/subdir2/movie.mov"
        };
        baseDirectory = TestUtils.buildTestDirectory(getClass());
        library = new MediaLibrary();
        library.setName("Media Library 1");
        library.setBaseDirectory(baseDirectory);

        for (String path : paths) {
            File file = TestUtils.buildFileRelativeToDirectory(baseDirectory, path);
            library.addMovieFile(file);
            files.add(file);
        }
    }

    @Test
    public void testContainsFiles() {
        for (File file : files) {
            assertTrue(library.containsFile(file.getName()));
        }
    }

}
