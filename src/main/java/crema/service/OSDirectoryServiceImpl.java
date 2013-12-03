package crema.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Provides services to obtain operating system specific directories.
 * 
 * @author Jason Andersen (andersen.jason@gmail.com)
 */
@Service
public class OSDirectoryServiceImpl implements OSDirectoryService {

    private static Logger log = LoggerFactory.getLogger(OSDirectoryServiceImpl.class);

    /**
     * @see crema.service.OSDirectoryService#getUserHomeDirectoryPath()
     */
    public String getUserHomeDirectoryPath() {
        String path = System.getProperty("user.home");
        log.debug("user home directory: {}", path);
        return path;
    }

}
