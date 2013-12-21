package crema.view.pivot;

import org.apache.pivot.collections.Map;
import org.apache.pivot.wtk.Application;
import org.apache.pivot.wtk.Display;
import org.apache.pivot.wtk.Window;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import crema.util.BeanContext;
import crema.view.pivot.window.Main;

/**
 * The primary class for the Crema Apache Pivot-based UI.
 * 
 * @author Jason Andersen (andersen.jason@gmail.com)
 */
public class CremaApplication implements Application {

    private static Logger log = LoggerFactory.getLogger(CremaApplication.class);

    private static final String APP_CONTEXT_PATH = "spring/applicationContext.xml";

    private Window window;

    private BxmlService bxmlService;

    /**
     * Constructor.
     */
    public CremaApplication() {
        log.info("Crema application initializing");
        initializeApplicationContext();
        bxmlService = BeanContext.getBean(BxmlService.class);
    }

    /**
     * Boot up using BXML.
     * @see org.apache.pivot.wtk.Application#startup(org.apache.pivot.wtk.Display, org.apache.pivot.collections.Map)
     */
    public void startup(final Display display, final Map<String, String> properties) throws Exception {
        log.info("startup called");
        window = bxmlService.readWindowFromBxml(Main.class, "main.bxml");
        window.open(display);
    }

    /**
     * @see org.apache.pivot.wtk.Application#shutdown(boolean)
     */
    public boolean shutdown(final boolean optional) throws Exception {
        log.info("shutdown called");
        if (window != null) {
            window.close();
        }
        return false;
    }

    /**
     * @see org.apache.pivot.wtk.Application#suspend()
     */
    public void suspend() throws Exception {
        log.info("suspend called");
    }

    /**
     * @see org.apache.pivot.wtk.Application#resume()
     */
    public void resume() throws Exception {
        log.info("resume called");
    }

    /**
     * Initialize Spring's application context.
     */
    private ApplicationContext initializeApplicationContext() {
        log.info("initializing Spring application context from {}", APP_CONTEXT_PATH);
        ApplicationContext context = new ClassPathXmlApplicationContext(APP_CONTEXT_PATH);
        ((ConfigurableApplicationContext) context).registerShutdownHook();
        return context;
    }
}
