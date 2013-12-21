package crema.test;

import static org.junit.Assert.assertTrue;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import crema.util.PathUtil;

/**
 * Testing utilities
 * 
 * @author Jason Andersen (andersen.jason@gmail.com)
 */
public class TestUtil {

    private static Logger log = LoggerFactory.getLogger(TestUtil.class);

    /**
     * Sets up a directory on the file system to use as a media directory for testing
     * @return a file object representing an existing directory to use for testing
     */
    public static File buildTestMediaDirectory(Class<?> callingClass) {
        String path = PathUtil.getJavaTempDirectoryPath();
        String directoryName = String.format("%s-%d", callingClass.getSimpleName(),
                System.currentTimeMillis());
        File directory = new File(String.format("%s%s", path, directoryName));
        log.debug("creating test media directory: {}", directory);
        boolean result = directory.mkdirs();
        log.debug("test media direction creation result: {}", result);
        assertTrue(directory.exists());
        assertTrue(directory.canRead());
        assertTrue(directory.canWrite());
        return directory;
    }
}
