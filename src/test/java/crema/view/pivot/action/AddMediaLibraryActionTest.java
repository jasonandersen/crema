package crema.view.pivot.action;

import static org.junit.Assert.assertNotNull;

import org.apache.pivot.wtk.Component;
import org.apache.pivot.wtk.FileBrowserSheet;
import org.apache.pivot.wtk.Window;
import org.junit.Before;
import org.junit.Ignore;
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

    private Window ownerWindow;

    private FileBrowserSheet fileBrowserSheet;

    private Component component;

    @Before
    public void setup() {
        //build mocks
        ownerWindow = Mockito.mock(Window.class);
        fileBrowserSheet = Mockito.mock(FileBrowserSheet.class);
        component = Mockito.mock(Component.class);

        action.setOwner(ownerWindow);
        action.setFileBrowserSheet(fileBrowserSheet);
    }

    @Test
    public void testDependencyInjection() {
        assertNotNull(action);
    }

    @Ignore
    @Test
    public void testRun() {
        action.perform(component);
        triggerFileSheetClosedEvent();
    }

    /**
     * Triggers the closed event.
     */
    private void triggerFileSheetClosedEvent() {
        fileBrowserSheet.getSheetCloseListener().sheetClosed(fileBrowserSheet);
    }

}
