package crema.service;

import java.io.File;

import crema.domain.MediaLibrary;
import crema.exception.MediaLibraryException;

/**
 * Provides service to interact with MediaLibrary objects.
 * 
 * @author Jason Andersen (andersen.jason@gmail.com)
 */
public interface MediaLibraryService {

    /**
     * Creates a {@link MediaLibrary} object.
     * @param path
     * @param name
     * @return the created MediaLibrary
     * @throws MediaLibraryException 
     */
    MediaLibrary createMediaLibrary(File path, String name) throws MediaLibraryException;

    /**
     * Reads a media library from persistence.
     * @param libraryName
     * @return an existing {@link MediaLibrary}, will return null null if no media library is found with a matching name
     */
    MediaLibrary readMediaLibrary(String libraryName);
}
