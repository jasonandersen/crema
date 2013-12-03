package crema.test.mock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import crema.service.OSDirectoryService;

/**
 * Mock implementation of {@link OSDirectoryService}. Will always return the
 * Java specified temp directory.
 * 
 * @author Jason Andersen (andersen.jason@gmail.com)
 */
@Primary
@Service
public class MockOSDirectoryService implements OSDirectoryService {

    private static Logger log = LoggerFactory.getLogger(MockOSDirectoryService.class);

    /**
     * @see crema.service.OSDirectoryService#getUserHomeDirectoryPath()
     */
    public String getUserHomeDirectoryPath() {
        String path = System.getProperty("java.io.tmpdir");
        log.debug("user home directory: {}", path);
        return path;
    }

}
