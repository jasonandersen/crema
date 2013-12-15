package crema.boot;

import org.apache.pivot.wtk.DesktopApplicationContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import crema.view.pivot.Crema;

/**
 * Boot strap the application.
 * 
 * @author Jason Andersen (andersen.jason@gmail.com)
 */
public class Main {

    private static Logger log = LoggerFactory.getLogger(Main.class);

    /**
     * Boot strap method. Let's get the party started!
     * @param args
     */
    public static void main(String[] args) {
        log.info("application booting up in desktop application mode");
        DesktopApplicationContext.main(Crema.class, args);
    }

}
