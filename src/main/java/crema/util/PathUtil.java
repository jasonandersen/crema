package crema.util;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.lang.Validate;
import org.springframework.core.io.ClassPathResource;

/**
 * Because Java is so goddamned infuriating when it comes to path issues.
 * 
 * @author Jason Andersen (andersen.jason@gmail.com)
 */
public class PathUtil {

    private PathUtil() {
        //no instantiation for you!
    }

    /**
     * @return the path representing Java's temp directory
     */
    public static String getJavaTempDirectoryPath() {
        return System.getProperty("java.io.tmpdir");
    }

    /**
     * @param path
     * @return the InputStream from a resource found on the class path
     * @throws IOException
     * @throws IllegalArgumentException when path is null
     */
    public static InputStream readInputStreamFromClassPath(final String path) throws IOException {
        Validate.notNull(path, "Path cannot be null.");
        ClassPathResource resource = new ClassPathResource(path);
        return resource.getInputStream();
    }
}
