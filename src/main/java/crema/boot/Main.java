package crema.boot;

import org.apache.pivot.wtk.DesktopApplicationContext;

import crema.view.pivot.CremaPivotApplication;

/**
 * Boot strap the application.
 * 
 * @author Jason Andersen (andersen.jason@gmail.com)
 */
public class Main {

    /**
     * Boot strap method. Let's get the party started!
     * @param args
     */
    public static void main(String[] args) {
        DesktopApplicationContext.main(CremaPivotApplication.class, args);
    }

}
