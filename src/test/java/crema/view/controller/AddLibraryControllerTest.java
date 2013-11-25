package crema.view.controller;

import org.junit.Assert;
import org.junit.Test;

import crema.domain.MediaLibrary;

/**
 * Tests the library
 * @author Jason Andersen (andersen.jason@gmail.com)
 * @since Oct 30, 2013
 */
public class AddLibraryControllerTest {

    private static final String DEFAULT_NAME = "New Media Library";

    private static final String DEFAULT_PATH = System.getProperties().getProperty("java.home");

    private final AddLibraryController controller = new AddLibraryController();

    private String path = DEFAULT_PATH;

    private String name = DEFAULT_NAME;

    private MediaLibrary library;

    @Test
    public void testAddLibrary() {
        library = controller.addLibrary(path, name);
        Assert.assertNotNull(library);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNullPath() {
        library = controller.addLibrary(null, name);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNullName() {
        library = controller.addLibrary(path, null);

    }

}
