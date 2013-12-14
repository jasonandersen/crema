package crema.boot;

import org.apache.pivot.wtk.DesktopApplicationContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import crema.view.pivot.CremaPivotApplication;

/**
 * Boot strap the application.
 * 
 * @author Jason Andersen (andersen.jason@gmail.com)
 */
public class Main {

    private static Logger log = LoggerFactory.getLogger(Main.class);

    private static final String APP_CONTEXT_PATH = "spring/applicationContext.xml";

    /**
     * Boot strap method. Let's get the party started!
     * @param args
     */
    public static void main(String[] args) {
        log.info("application booting up in desktop application mode");
        initializeApplicationContext();
        DesktopApplicationContext.main(CremaPivotApplication.class, args);
    }

    /**
     * initialize Spring's application context
     */
    private static ApplicationContext initializeApplicationContext() {
        log.info("initializing Spring application context from {}", APP_CONTEXT_PATH);
        ApplicationContext context = new ClassPathXmlApplicationContext(APP_CONTEXT_PATH);
        ((ConfigurableApplicationContext) context).registerShutdownHook();
        return context;
    }

}
