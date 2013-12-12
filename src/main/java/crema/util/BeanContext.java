package crema.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Provides access to singleton beans.
 * 
 * @author Jason Andersen (andersen.jason@gmail.com)
 */
public class BeanContext {

    /*
     * TODO - how do I get test contexts to load dynamically in here??
     */

    private static Logger log = LoggerFactory.getLogger(BeanContext.class);
    private static final ApplicationContext context;

    /**
     * instantiate context instance
     */
    static {
        log.info("setting singleton reference to ApplicationContext");
        context = new ClassPathXmlApplicationContext("spring/applicationContext.xml");
    }

    /**
     * Private constructor - only static access
     */
    private BeanContext() {
        //no instantiation for you!
    }

    /**
     * Loads a bean of the specified type
     * @param type
     * @return
     */
    public static <T> T getBean(Class<T> type) {
        return context.getBean(type);
    }

    /**
     * Loads a bean by the name of the bean
     * @param name
     * @param expectedType
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> T getBean(String name, Class<T> expectedType) {
        Object bean = context.getBean(name);
        return (T) bean;
    }

    /**
     * Refreshes the entire application context. Use with caution.
     */
    public static void refreshContext() {
        log.warn("refreshing ApplicationContext");
        ((ConfigurableApplicationContext) context).refresh();
    }
}
