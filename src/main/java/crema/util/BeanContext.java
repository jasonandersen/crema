package crema.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

/**
 * Provides access to singleton beans.
 * 
 * @author Jason Andersen (andersen.jason@gmail.com)
 */
@Component
public class BeanContext implements ApplicationContextAware {

    private static Logger log = LoggerFactory.getLogger(BeanContext.class);
    private static ApplicationContext context;

    /**
     * Private constructor - only static access.
     */
    private BeanContext() {
        //no instantiation for you!
    }

    /**
     * Loads a bean of the specified type.
     * @param type
     * @return
     */
    public static <T> T getBean(final Class<T> type) {
        return context.getBean(type);
    }

    /**
     * Loads a bean by the name of the bean.
     * @param name
     * @param expectedType
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> T getBean(final String name, final Class<T> expectedType) {
        Object bean = context.getBean(name);
        return (T) bean;
    }

    /**
     * Refreshes the entire application context. Use with caution.
     */
    public static void refreshContext() {
        log.warn("refreshing ApplicationContext");
        ConfigurableApplicationContext configAppContext = (ConfigurableApplicationContext) context;
        configAppContext.close();
        configAppContext.start();
    }

    /**
     * @see org.springframework.context.ApplicationContextAware#setApplicationContext(
     *      org.springframework.context.ApplicationContext)
     */
    public void setApplicationContext(final ApplicationContext applicationContext) {
        log.info("setting singleton reference to ApplicationContext");
        context = applicationContext;
    }

}
