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

    private final AddLibraryController controller = new AddLibraryController();

    private String path;

    private String name;

    private MediaLibrary library;

    @Test
    public void testAddLibrary() {
        library = controller.addLibrary(path, name);
        Assert.assertNotNull(library);
    }

}
