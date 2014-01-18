package crema.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import crema.exception.CremaException;
import crema.exception.MediaFileException;
import crema.test.TestUtils;

/**
 * Tests the addMovie() method of the {@link MediaLibrary} class.
 * 
 * @author Jason Andersen (andersen.jason@gmail.com)
 */
public class MediaLibraryAddMovieTest {

    private MediaLibrary library;

    private File baseDirectory;

    @Before
    public void setupLibrary() {
        baseDirectory = TestUtils.buildTestDirectory(getClass());
        library = new MediaLibrary();
        library.setName("Media Library 1");
        library.setBaseDirectory(baseDirectory);
    }

    @After
    public void tearDownDirectories() throws IOException {
        FileUtils.deleteDirectory(baseDirectory);
    }

    @Test
    public void testSingleFile() throws IOException, CremaException {
        String relativePath = "sub-dir1/sub-dir1a/movie1.avi";
        File testFile = TestUtils.buildFileRelativeToDirectory(baseDirectory, relativePath);
        library.addMovie(testFile);

        Movie movie = library.getMoviesByFilePath().get(relativePath);
        assertNotNull(movie);
        assertEquals(relativePath, movie.getFirstMediaFile().getRelativePath());
    }

    @Test
    public void testMultipleFiles() throws IOException, CremaException {
        String[] paths = new String[] {
                "sub-dir1/sub-dir2/movie.avi",
                "sub-dir1/sub-dir2/movie.wmv",
                "sub-dir1/sub-dir2/movie.mkv",
                "sub-dir1/sub-dir2/movie.mpg",
                "sub-dir1/sub-dir2/movie.mpe",
                "sub-dir1/sub-dir2/movie.mpeg",
                "sub-dir1/sub-dir2/movie.mov",
                "sub-dir1/sub-dir2/movie.flv",
                "sub-dir1/sub-dir2/movie.m4v"
        };
        for (String relativePath : paths) {
            File testFile = TestUtils.buildFileRelativeToDirectory(baseDirectory, relativePath);
            testFile.deleteOnExit();
            library.addMovie(testFile);
        }
        for (String relativePath : paths) {
            Movie movie = library.getMoviesByFilePath().get(relativePath);
            assertNotNull(movie);
            assertEquals(relativePath, movie.getFirstMediaFile().getRelativePath());
        }
    }

    @Test(expected = MediaFileException.class)
    public void testFileOutsideMediaLibrary() throws IOException, InterruptedException, CremaException {
        /*
         * This test can fail if it runs too fast because the directory name is based on System.currentTimeMillis()
         * so we pause long enough so it won't fail.
         */
        Thread.sleep(50);
        File invalidFile = TestUtils.buildFileRelativeToDirectory(
                TestUtils.buildTestDirectory(getClass()),
                "sub-dir1/movie.avi");
        invalidFile.deleteOnExit();
        library.addMovie(invalidFile);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNullFile() throws CremaException {
        File nullArg = null;
        library.addMovie(nullArg);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testDeletingAMovie() throws IOException, CremaException {
        String relativePath = "sub-dir1/sub-dir1a/movie1.avi";
        File testFile = TestUtils.buildFileRelativeToDirectory(baseDirectory, relativePath);
        library.addMovie(testFile);

        for (String key : library.getMoviesByFilePath().keySet()) {
            library.getMoviesByFilePath().remove(key);
        }
    }
}
