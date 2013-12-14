package crema.dao.db4o;

import java.io.File;
import java.io.IOException;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import crema.exception.CremaException;
import crema.exception.PreferencesException;
import crema.service.CremaDirectoryLocator;
import crema.service.Preferences;

/**
 * Utility to retrieve the local database file.
 * 
 * @author Jason Andersen (andersen.jason@gmail.com)
 */
@Component
public class DatabaseFileLocator {

    private static Logger log = LoggerFactory.getLogger(DatabaseFileLocator.class);

    @Autowired
    @Qualifier("defaultDatabaseFile")
    private String defaultDatabaseFileName;

    @Autowired
    private Preferences preferences;

    @Autowired
    private CremaDirectoryLocator cremaDirectoryLocator;

    private File databaseFile;

    /**
     * Initialize the database file
     * @throws CremaException
     * @throws IOException
     */
    @PostConstruct
    public void initialize() throws CremaException, IOException {
        log.info("initializing");
        String path = preferences.getString(Preferences.KEY_CREMA_DB_FILE);
        if (path == null) {
            path = createDefaultFilePath();
            preferences.putString(Preferences.KEY_CREMA_DB_FILE, path);
        }
        File file = new File(path);
        if (!file.exists()) {
            createDatabaseFile(file);
        }
        log.info("setting database file path as {}", file);
        databaseFile = file;
    }

    /**
     * @return a string containing the path for the database file, will not return null
     */
    public String getPath() {
        String path = databaseFile.getPath();
        log.debug("using database file {}", path);
        return path;
    }

    /**
     * Creates the database file
     * @param file
     * @throws IOException 
     */
    private void createDatabaseFile(File file) throws IOException {
        if (file.exists()) {
            return;
        }
        log.info("creating database file: {}", file);
        file.createNewFile();
    }

    /**
     * @return a string containing the default database file path
     * @throws PreferencesException 
     */
    private String createDefaultFilePath() throws PreferencesException {
        StringBuilder path = new StringBuilder();
        path.append(cremaDirectoryLocator.getCremaDirectory().getPath());
        path.append(File.separator);
        path.append(defaultDatabaseFileName);
        log.info("building default database file name: {}", path);
        return path.toString();
    }
}
