package crema.service;

import org.springframework.stereotype.Service;

/**
 * Provides services to obtain operating system specific directories.
 * 
 * @author Jason Andersen (andersen.jason@gmail.com)
 */
@Service
public class OSDirectoryServiceImpl implements OSDirectoryService {

    /**
     * @see crema.service.OSDirectoryService#getUserHomeDirectoryPath()
     */
    public String getUserHomeDirectoryPath() {
        return System.getProperty("user.home");
    }

}
