package crema.service;

import java.io.File;
import java.util.prefs.Preferences;

import org.apache.commons.lang.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import crema.exception.PreferencesException;

/**
 * Implementation of {@link PreferencesService} using the Java Preferences API.
 * 
 * @author Jason Andersen (andersen.jason@gmail.com)
 * @see crema.service.PreferencesService
 */
@Service
public class PreferencesServiceImpl implements PreferencesService {

    private static final String PREF_CREMA_DIR = "crema.dir";

    private static final String CREMA_DIR_NAME = ".crema";

    private static Logger log = LoggerFactory.getLogger(PreferencesServiceImpl.class);

    @Autowired
    private OSDirectoryService osDirService;

    private final Preferences preferences;

    /**
     * Constructor
     */
    public PreferencesServiceImpl() {
        log.info("instantiating user preferences for class {}", getClass());
        preferences = Preferences.userNodeForPackage(getClass());
    }

    /**
     * @see crema.service.PreferencesService#getCremaDirectory()
     */
    public File getCremaDirectory() throws PreferencesException {
        File dir = loadDirectoryFromPreferences();
        if (!dir.exists()) {
            createCremaDir(dir);
        }
        validateDirectory(dir);
        return dir;
    }

    /**
     * Setter method to enable testing.
     * @param osDirService
     */
    public void setOSDirectoryService(OSDirectoryService osDirService) {
        this.osDirService = osDirService;
    }

    /**
     * @return the crema directory stored in the Java preferences, will return a default value if not found; 
     *      will not return null
     */
    private File loadDirectoryFromPreferences() {
        log.debug("reading crema directory from preferences key: {}", PREF_CREMA_DIR);
        String path = preferences.get(PREF_CREMA_DIR, buildDefaultCremaDirectoryPath());
        return new File(path);
    }

    /**
     * Creates the crema directory on disk
     * @param dir
     * @throws PreferencesException when the directory could not be created
     */
    private void createCremaDir(File dir) throws PreferencesException {
        Validate.notNull(dir);
        if (dir.exists()) {
            return;
        }
        log.info("creating crema directory: {}", dir);
        boolean success = dir.mkdirs();
        if (!success) {
            throw new PreferencesException(
                    String.format("The crema directory %s could not be created.", dir.getPath()));
        }
    }

    /**
     * Validates the directory
     * @param dir
     */
    private void validateDirectory(File dir) throws PreferencesException {
        log.debug("validating crema directory: {}", dir);
        if (!dir.exists()) {
            throw new PreferencesException(String.format("Crema directory %s does not exist.", dir.getPath()));
        }
        if (!dir.isDirectory()) {
            throw new PreferencesException(String.format("Crema directory %s is not a directory.", dir.getPath()));
        }
        if (!dir.canRead()) {
            throw new PreferencesException(String.format("Crema directory %s can not be read.", dir.getPath()));
        }
        if (!dir.canWrite()) {
            throw new PreferencesException(String.format("Crema directory %s can not be written to.", dir.getPath()));
        }
    }

    /**
     * @return the path for the default crema directory
     */
    private String buildDefaultCremaDirectoryPath() {
        StringBuffer path = new StringBuffer();
        String userHomeDir = osDirService.getUserHomeDirectoryPath();
        path.append(userHomeDir);
        if (!userHomeDir.endsWith(File.separator)) {
            path.append(File.separator);
        }
        path.append(CREMA_DIR_NAME);
        log.debug("building default crema directory: {}", path);
        return path.toString();
    }
}
