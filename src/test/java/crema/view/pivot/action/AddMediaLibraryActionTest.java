package crema.view.pivot.action;

import static org.junit.Assert.assertNotNull;

import org.apache.pivot.wtk.Window;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;

import crema.test.AbstractIntegrationTest;

/**
 * Testing the {@link AddMediaLibraryAction} class.
 * 
 * @author Jason Andersen (andersen.jason@gmail.com)
 */
public class AddMediaLibraryActionTest extends AbstractIntegrationTest {

    @Autowired
    private AddMediaLibraryAction action;

    @Before
    public void setup() {
        action.setOwner(mockOwnerWindow());
    }

    @Test
    public void testDependencyInjection() {
        assertNotNull(action);
    }

    /**
     * @return
     */
    private Window mockOwnerWindow() {
        return Mockito.spy(new Window());
    }

}
