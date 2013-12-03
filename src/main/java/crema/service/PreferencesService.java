package crema.service;

import java.io.File;

import crema.exception.PreferencesException;

/**
 * Provides application preferences.
 * 
 * @author Jason Andersen (andersen.jason@gmail.com)
 */
public interface PreferencesService {

    /**
     * @return a file reference to the crema directory, guaranteed to exist and be writeable
     * @throws PreferencesException when crema directory cannot be found
     * @throws PreferencesException when crema directory cannot be created
     * @throws PreferencesException when crema directory cannot be written to
     */
    public File getCremaDirectory() throws PreferencesException;

}
