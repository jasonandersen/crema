package crema.ui.pivot;

import java.awt.Color;
import java.awt.Font;

import org.apache.pivot.collections.Map;
import org.apache.pivot.wtk.Application;
import org.apache.pivot.wtk.Display;
import org.apache.pivot.wtk.HorizontalAlignment;
import org.apache.pivot.wtk.Label;
import org.apache.pivot.wtk.VerticalAlignment;
import org.apache.pivot.wtk.Window;

/**
 * The primary class for the crema Apache Pivot-based UI.
 * 
 * @author Jason Andersen (andersen.jason@gmail.com)
 */
public class CremaPivotApplication implements Application {

    private Window window = null;

    /**
     * @see org.apache.pivot.wtk.Application#startup(org.apache.pivot.wtk.Display, org.apache.pivot.collections.Map)
     */
    public void startup(Display display, Map<String, String> properties)
            throws Exception {

        window = new Window();

        Label label = new Label();
        label.setText("Hello World!");
        label.getStyles().put("font", new Font("Arial", Font.BOLD, 24));
        label.getStyles().put("color", Color.RED);
        label.getStyles()
                .put("horizontalAlignment", HorizontalAlignment.CENTER);
        label.getStyles().put("verticalAlignment", VerticalAlignment.CENTER);

        window.setContent(label);
        window.setTitle("Hello World!");
        window.setMaximized(true);

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
