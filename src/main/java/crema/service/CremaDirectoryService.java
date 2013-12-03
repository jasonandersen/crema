package crema.service;

import java.io.File;

import crema.exception.PreferencesException;

/**
 * Provides access to the default crema directory used to store application data into.
 * 
 * @author Jason Andersen (andersen.jason@gmail.com)
 */
public interface CremaDirectoryService {

    /**
     * @return a file reference to the crema directory, guaranteed to exist and be writeable
     * @throws PreferencesException when crema directory cannot be found
     * @throws PreferencesException when crema directory cannot be created
     * @throws PreferencesException when crema directory cannot be written to
     */
    public File getCremaDirectory() throws PreferencesException;

    /**
     * @return true if the crema directory has already been specified by a previous session
     */
    public boolean isCremaDirectorySpecified();

}
