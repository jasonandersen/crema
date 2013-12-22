package crema.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.lang.Validate;
import org.springframework.core.io.ClassPathResource;

/**
 * Because Java is so goddamned infuriating when it comes to path issues.
 * 
 * @author Jason Andersen (andersen.jason@gmail.com)
 */
public class PathUtils {

    private static final String EXT_DELIMITER = ".";

    private PathUtils() {
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

    /**
     * @param file
     * @return the extension of a file, will return null if the file is null or an
     *      empty string if the file has no extension
     */
    public static String getFileExtension(final File file) {
        if (file == null) {
            return null;
        }
        String path = file.getPath();
        int index = path.lastIndexOf(EXT_DELIMITER);
        if (index < 0) {
            return "";
        }
        return path.substring(index + 1);
    }

}
