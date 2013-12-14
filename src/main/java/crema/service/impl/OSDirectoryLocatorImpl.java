package crema.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import crema.service.OSDirectoryLocator;

/**
 * Provides services to obtain operating system specific directories.
 * 
 * @author Jason Andersen (andersen.jason@gmail.com)
 */
@Service
public class OSDirectoryLocatorImpl implements OSDirectoryLocator {

    private static Logger log = LoggerFactory.getLogger(OSDirectoryLocatorImpl.class);

    /**
     * @see crema.service.OSDirectoryLocator#getUserHomeDirectoryPath()
     */
    public String getUserHomeDirectoryPath() {
        String path = System.getProperty("user.home");
        log.debug("user home directory: {}", path);
        return path;
    }

}
