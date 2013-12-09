package crema.service.impl;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import crema.exception.PreferencesException;
import crema.service.CremaDirectoryService;
import crema.service.OSDirectoryService;
import crema.service.PreferencesService;

/**
 * Implementation of {@link CremaDirectoryService}.
 * 
 * @author Jason Andersen (andersen.jason@gmail.com)
 * @see crema.service.CremaDirectoryService
 */
@Service
public class CremaDirectoryServiceImpl implements CremaDirectoryService {

    private static final String CREMA_DIR_NAME = ".crema";

    private static Logger log = LoggerFactory.getLogger(CremaDirectoryServiceImpl.class);

    @Autowired
    private OSDirectoryService osDirService;

    @Autowired
    private PreferencesService preferencesService;

    /**
     * @see crema.service.CremaDirectoryService#isCremaDirectorySpecified()
     */
    public boolean isCremaDirectorySpecified() {
        String path = preferencesService.getString(PreferencesService.KEY_CREMA_DIRECTORY);
        return path != null;
    }

    /**
     * @see crema.service.CremaDirectoryService#getCremaDirectory()
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
     * @param osDirService
     */
    public void setOSDirectoryService(OSDirectoryService osDirService) {
        this.osDirService = osDirService;
    }

    /**
     * @return the crema directory stored in the Java preferences, will return null if not found
     */
    private File loadDirectoryFromPreferences() {
        log.debug("reading crema directory from preferences key: {}", PreferencesService.KEY_CREMA_DIRECTORY);
        String path = preferencesService.getString(PreferencesService.KEY_CREMA_DIRECTORY);
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
        preferencesService.putString(PreferencesService.KEY_CREMA_DIRECTORY, dir.getPath());
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
        String userHomeDir = osDirService.getUserHomeDirectoryPath();
        path.append(userHomeDir);
        if (!userHomeDir.endsWith(File.separator)) {
            path.append(File.separator);
        }
        path.append(CREMA_DIR_NAME);
        path.append(File.separator);
        log.debug("building default crema directory: {}", path);
        return path.toString();
    }

}
