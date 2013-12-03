package crema.cucumber;

import static org.junit.Assert.assertTrue;

import java.io.File;

import org.springframework.test.context.ContextConfiguration;

import crema.util.PathUtil;

/**
 * Base class for cucumber step definition classes.
 * 
 * @author Jason Andersen (andersen.jason@gmail.com)
 */
@ContextConfiguration(locations = { "classpath:crema/cucumber/cucumber.xml" })
public abstract class AbstractCucumberStepDefs {

    /**
     * Sets up a directory on the file system to use as a media directory for testing
     * @return a file object representing an existing directory to use for testing
     */
    protected File buildTestMediaDirectory() {
        String path = PathUtil.getJavaTempDirectoryPath();
        String directoryName = String.format("%s-%d", getClass().getSimpleName(), System.currentTimeMillis());
        File directory = new File(String.format("%s%s", path, directoryName));
        directory.mkdirs();
        assertTrue(directory.exists());
        assertTrue(directory.canRead());
        assertTrue(directory.canWrite());
        return directory;
    }
}
