package crema.boot;

import org.apache.pivot.wtk.DesktopApplicationContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import crema.view.pivot.CremaApplication;

/**
 * Boot strap the application into Desktop mode.
 * 
 * @author Jason Andersen (andersen.jason@gmail.com)
 */
public class Crema {

    private static Logger log = LoggerFactory.getLogger(Crema.class);

    /**
     * No instantiation for you!
     */
    private Crema() {
        //noop
    }

    /**
     * Boot strap method. Let's get the party started!
     * @param args
     */
    public static void main(final String[] args) {
        log.info("application booting up in desktop application mode");
        DesktopApplicationContext.main(CremaApplication.class, args);
    }

}
