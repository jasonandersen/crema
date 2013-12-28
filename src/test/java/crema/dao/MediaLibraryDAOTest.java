package crema.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;

import java.io.File;
import java.io.IOException;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import crema.domain.MediaLibrary;
import crema.domain.Movie;
import crema.exception.DuplicateMediaLibraryException;
import crema.exception.MediaFileException;
import crema.test.AbstractIntegrationTest;
import crema.test.TestUtils;

/**
 * Testing the {@link MediaLibraryDAO} implementation.
 * 
 * @author Jason Andersen (andersen.jason@gmail.com)
 */
public class MediaLibraryDAOTest extends AbstractIntegrationTest {

    @Autowired
    private MediaLibraryDAO dao;

    private MediaLibrary library;

    private File directory;

    @Before
    public void setupLibrary() {
        directory = TestUtils.buildTestDirectory(getClass());
        library = new MediaLibrary();
        library.setName("I like monkeys");
        library.setBaseDirectory(directory);
    }

    @Test
    public void testDependencyInjection() {
        assertNotNull(dao);
    }

    @Test
    public void testSaveNoFiles() throws DuplicateMediaLibraryException {
        String name = library.getName();
        dao.save(library);
        MediaLibrary savedLibrary = dao.read(name);
        assertNotNull(savedLibrary);
        assertSame(library, savedLibrary);
    }

    @Test(expected = DuplicateMediaLibraryException.class)
    public void testDuplicateMediaLibraryName() throws DuplicateMediaLibraryException {
        MediaLibrary duplicate = new MediaLibrary();
        duplicate.setName("I like monkeys");
        duplicate.setBaseDirectory(TestUtils.buildTestDirectory(getClass()));
        dao.save(library);
        dao.save(duplicate);
    }

    @Test
    public void testMovieIsSavedCorrectly() throws IOException, MediaFileException, DuplicateMediaLibraryException {
        File file = TestUtils.buildFileRelativeToDirectory(directory, "movie.mpg");
        library.addMovieFile(file);
        assertFalse(library.getMoviesByFilePath().isEmpty());

        dao.save(library);
        MediaLibrary savedLibrary = dao.read(library.getName());
        Movie movie = savedLibrary.getMoviesByFilePath().values().iterator().next();

        assertSame(library, savedLibrary);
        assertFalse(savedLibrary.getMoviesByFilePath().isEmpty());
        assertEquals("movie.mpg", movie.getMediaFile().getRelativePath());
    }

    @Test
    public void testReadAll() throws DuplicateMediaLibraryException {
        dao.save(library);
        library = null;
        setupLibrary();
        library.setName("Here is a new name");
        dao.save(library);

        Collection<MediaLibrary> libraries = dao.readAll();
        assertEquals(2, libraries.size());
    }

}
