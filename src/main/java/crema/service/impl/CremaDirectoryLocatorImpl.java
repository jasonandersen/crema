package crema.service.impl;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import crema.exception.PreferencesException;
import crema.service.CremaDirectoryLocator;
import crema.service.OSDirectoryLocator;
import crema.service.Preferences;

/**
 * Implementation of {@link CremaDirectoryLocator}.
 * 
 * @author Jason Andersen (andersen.jason@gmail.com)
 * @see crema.service.CremaDirectoryLocator
 */
@Service
public class CremaDirectoryLocatorImpl implements CremaDirectoryLocator {

    private static Logger log = LoggerFactory.getLogger(CremaDirectoryLocatorImpl.class);

    @Autowired
    @Qualifier("defaultCremaDirectoryName")
    private String defaultCremaDirectoryName;

    @Autowired
    private OSDirectoryLocator osDirLocator;

    @Autowired
    private Preferences preferencesService;

    /**
     * @see crema.service.CremaDirectoryLocator#isCremaDirectorySpecified()
     */
    public boolean isCremaDirectorySpecified() {
        String path = preferencesService.getString(Preferences.KEY_CREMA_DIRECTORY);
        return path != null;
    }

    /**
     * @see crema.service.CremaDirectoryLocator#getCremaDirectory()
     */
    public File getCremaDirectory() throws PreferencesException {
        File dir = loadDirectoryFromPreferences();
        if (dir == null || !dir.exists()) {
            dir = createCremaDir();
        }
        validateDirectory(dir);
        return dir;
    }

    /**
     * Setter method to enable testing.
     * @param osDirLocator
     */
    public void setOSDirectoryService(OSDirectoryLocator osDirLocator) {
        this.osDirLocator = osDirLocator;
    }

    /**
     * @return the crema directory stored in the Java preferences, will return null if not found
     */
    private File loadDirectoryFromPreferences() {
        log.debug("reading crema directory from preferences key: {}", Preferences.KEY_CREMA_DIRECTORY);
        String path = preferencesService.getString(Preferences.KEY_CREMA_DIRECTORY);
        return path != null ? new File(path) : null;
    }

    /**
     * Creates the crema directory on disk
     * @throws PreferencesException when the directory could not be created
     */
    private File createCremaDir() throws PreferencesException {
        String path = buildDefaultCremaDirectoryPath();
        File dir = new File(path);
        if (!dir.exists()) {
            log.info("creating crema directory: {}", dir);
            boolean success = dir.mkdirs();
            if (!success) {
                throw new PreferencesException(
                        String.format("The crema directory %s could not be created.", dir.getPath()));
            }
        }
        preferencesService.putString(Preferences.KEY_CREMA_DIRECTORY, dir.getPath());
        return dir;
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
        String userHomeDir = osDirLocator.getUserHomeDirectoryPath();
        path.append(userHomeDir);
        if (!userHomeDir.endsWith(File.separator)) {
            path.append(File.separator);
        }
        path.append(defaultCremaDirectoryName);
        path.append(File.separator);
        log.debug("building default crema directory: {}", path);
        return path.toString();
    }

}
