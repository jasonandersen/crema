package crema.test.beans;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import crema.service.OSDirectoryLocator;
import crema.util.PathUtil;

/**
 * Mock implementation of {@link OSDirectoryLocator}. Will always return the
 * Java specified temp directory.
 * 
 * @author Jason Andersen (andersen.jason@gmail.com)
 */
@Primary
@Service
public class OSDirectoryServiceTempDirectoryImpl implements OSDirectoryLocator {

    private static Logger log = LoggerFactory.getLogger(OSDirectoryServiceTempDirectoryImpl.class);

    /**
     * @see crema.service.OSDirectoryLocator#getUserHomeDirectoryPath()
     */
    public String getUserHomeDirectoryPath() {
        String path = PathUtil.getJavaTempDirectoryPath();
        log.debug("user home directory: {}", path);
        return path;
    }

}
