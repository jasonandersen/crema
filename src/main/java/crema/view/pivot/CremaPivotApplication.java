package crema.view.pivot;

import org.apache.pivot.beans.BXML;
import org.apache.pivot.collections.Map;
import org.apache.pivot.wtk.Application;
import org.apache.pivot.wtk.Display;
import org.apache.pivot.wtk.Label;
import org.apache.pivot.wtk.Window;

import crema.util.BeanContext;

/**
 * The primary class for the crema Apache Pivot-based UI.
 * 
 * @author Jason Andersen (andersen.jason@gmail.com)
 */
public class CremaPivotApplication implements Application {

    private Window window;

    private BxmlService bxmlService;

    @BXML
    public Label label;

    public CremaPivotApplication() {
        bxmlService = BeanContext.getBean(BxmlService.class);
    }

    /**
     * Boot up using BXML
     * @see org.apache.pivot.wtk.Application#startup(org.apache.pivot.wtk.Display, org.apache.pivot.collections.Map)
     */
    public void startup(Display display, Map<String, String> properties) throws Exception {
        window = bxmlService.readWindowFromBxml(getClass(), "main.bxml");
        window.open(display);
    }

    /**
     * @see org.apache.pivot.wtk.Application#shutdown(boolean)
     */
    public boolean shutdown(boolean optional) throws Exception {
        if (window != null) {
            window.close();
        }
        return false;
    }

    /**
     * @see org.apache.pivot.wtk.Application#suspend()
     */
    public void suspend() throws Exception {
        //noop
    }

    /**
     * @see org.apache.pivot.wtk.Application#resume()
     */
    public void resume() throws Exception {
        //noop
    }

}
