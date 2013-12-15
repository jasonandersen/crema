package crema.view.pivot.window;

import java.net.URL;

import org.apache.pivot.beans.BXML;
import org.apache.pivot.beans.Bindable;
import org.apache.pivot.collections.Map;
import org.apache.pivot.util.Resources;
import org.apache.pivot.wtk.Label;
import org.apache.pivot.wtk.Window;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The primary UI window for Crema.
 * 
 * @author Jason Andersen (andersen.jason@gmail.com)
 */
public class Main extends Window implements Bindable {

    private static Logger log = LoggerFactory.getLogger(Main.class);

    @BXML
    private Label label;

    /**
     * @see org.apache.pivot.beans.Bindable#initialize(org.apache.pivot.collections.Map, java.net.URL, org.apache.pivot.util.Resources)
     */
    public void initialize(Map<String, Object> namespace, URL location, Resources resources) {
        log.debug("initialize called");
        log.debug("label value: {}", label);
        if (label != null) {
            label.setText("I LIKE MONKEYS!");
        }
    }

}
