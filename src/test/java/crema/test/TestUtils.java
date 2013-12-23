package crema.test;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import crema.util.PathUtils;

/**
 * Testing utilities
 * 
 * @author Jason Andersen (andersen.jason@gmail.com)
 */
public class TestUtils {

    private static Logger log = LoggerFactory.getLogger(TestUtils.class);

    private static Set<File> fileCleanup = new HashSet<File>();

    /**
     * Add a JVM shutdown hook to clean up our test directories. 
     */
    static {
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                cleanupFileSystem();
            }
        });
    }

    /**
     * Cleanup all the directories we created during the tests.
     */
    private static void cleanupFileSystem() {
        for (File file : fileCleanup) {
            if (file.exists()) {
                log.debug("cleaning up file {}", file);
                FileUtils.deleteQuietly(file);
            }
        }
    }

    /**
     * Sets up a directory on the file system to use as a media directory for testing.
     * NOTE: this directory will be deleted when the JVM exits. Do not use this for a
     * directory you'd like to keep around!
     * @return a file object representing an existing directory to use for testing
     */
    public static File buildTestDirectory(Class<?> callingClass) {
        String path = PathUtils.getJavaTempDirectoryPath();
        if (!path.endsWith(File.separator)) {
            path = path + File.separator;
        }
        String directoryName = String.format("%s-%d", callingClass.getSimpleName(),
                System.currentTimeMillis());
        File directory = new File(String.format("%s%s", path, directoryName));
        log.debug("creating test media directory: {}", directory);
        boolean result = directory.mkdirs();
        log.debug("test media direction creation result: {}", result);
        assertTrue(directory.exists());
        assertTrue(directory.canRead());
        assertTrue(directory.canWrite());
        fileCleanup.add(directory);
        return directory;
    }

    /**
     * Builds a file within an existing directory given a relative path. NOTE: this file will be
     * deleted when the JVM exits.
     * @param existingDirectory
     * @param relativeFilePath
     * @return the created file
     * @throws IOException 
     */
    public static File buildFileRelativeToDirectory(final File existingDirectory, final String relativeFilePath)
            throws IOException {

        Validate.isTrue(existingDirectory.exists(), "directory does not exist!");
        Validate.isTrue(existingDirectory.canWrite(), "directory cannot be written to!");
        Validate.isTrue(existingDirectory.isDirectory(), "directory is not a directory!");

        StringBuilder path = new StringBuilder(existingDirectory.getPath());
        if (!existingDirectory.getPath().endsWith(File.separator)) {
            path.append(File.separator);
        }
        path.append(relativeFilePath);

        File file = new File(path.toString());
        FileUtils.touch(file);
        fileCleanup.add(file);

        Validate.isTrue(file.exists(), "file does not exist!");
        log.debug("built file relative to an existing directory: {}", file);

        return file;
    }
}
