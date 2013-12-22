package crema.cucumber;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import crema.test.beans.DatabaseTruncator;

/**
 * Base class for cucumber step definition classes.
 * 
 * @author Jason Andersen (andersen.jason@gmail.com)
 */
@ContextConfiguration(locations = { "classpath:crema/cucumber/cucumber.xml" })
public abstract class AbstractCucumberStepDefs {

    @Autowired
    private DatabaseTruncator truncator;

    /**
     * Clean out the database after each test.
     */
    protected void truncateDatabase() {
        truncator.truncate();
    }

    /**
     * Cleans out a directory
     * @param directory
     * @throws IOException 
     */
    protected void deleteDirectory(File directory) throws IOException {
        if (directory == null) {
            return;
        }
        FileUtils.deleteDirectory(directory);
    }
}
