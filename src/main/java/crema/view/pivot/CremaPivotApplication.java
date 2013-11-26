package crema.view.pivot;

import java.io.InputStream;

import org.apache.pivot.beans.BXMLSerializer;
import org.apache.pivot.collections.Map;
import org.apache.pivot.wtk.Application;
import org.apache.pivot.wtk.Display;
import org.apache.pivot.wtk.Window;

import crema.controller.MainController;
import crema.util.BeanContext;
import crema.util.PathUtil;

/**
 * The primary class for the crema Apache Pivot-based UI.
 * 
 * @author Jason Andersen (andersen.jason@gmail.com)
 */
public class CremaPivotApplication implements Application {

    private Window window = null;

    private MainController mainController;

    public CremaPivotApplication() {
        mainController = BeanContext.getBean(MainController.class);
    }

    /**
     * Boot up using BXML
     * @see org.apache.pivot.wtk.Application#startup(org.apache.pivot.wtk.Display, org.apache.pivot.collections.Map)
     */
    public void startup(Display display, Map<String, String> properties) throws Exception {
        BXMLSerializer bxmlSerializer = new BXMLSerializer();
        InputStream is = PathUtil.readInputStreamFromClassPath("bxml/main.bxml");
        window = (Window) bxmlSerializer.readObject(is);
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
