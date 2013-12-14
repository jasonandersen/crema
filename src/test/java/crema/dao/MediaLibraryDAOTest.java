package crema.dao;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import crema.domain.MediaLibrary;
import crema.exception.DuplicateMediaLibraryException;
import crema.test.AbstractIntegrationTest;
import crema.test.TestUtil;

/**
 * Testing the {@link MediaLibraryDAO} implementation.
 * 
 * @author Jason Andersen (andersen.jason@gmail.com)
 */
public class MediaLibraryDAOTest extends AbstractIntegrationTest {

    @Autowired
    private MediaLibraryDAO dao;

    private MediaLibrary library;

    @Before
    public void setupLibrary() {
        library = new MediaLibrary();
        library.setName("I like monkeys");
        library.setBaseDirectory(TestUtil.buildTestMediaDirectory(getClass()));
    }

    @Test
    public void testDependencyInjection() {
        assertNotNull(dao);
    }

    @Test
    public void testSave() throws DuplicateMediaLibraryException {
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
        duplicate.setBaseDirectory(TestUtil.buildTestMediaDirectory(getClass()));
        dao.save(library);
        dao.save(duplicate);
    }

}
