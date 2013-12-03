package crema.service.impl;

import java.io.File;

import org.springframework.stereotype.Service;

import crema.domain.MediaLibrary;
import crema.exception.MediaLibraryException;
import crema.service.MediaLibraryService;

/**
 * Implementation of the {@link MediaLibraryService} service.
 * 
 * @author Jason Andersen (andersen.jason@gmail.com)
 */
@Service
public class MediaLibraryServiceImpl implements MediaLibraryService {

    /**
     * @see crema.service.MediaLibraryService#createMediaLibrary(java.io.File, java.lang.String)
     */
    public MediaLibrary createMediaLibrary(File path, String name) throws MediaLibraryException {

        return null;
    }

    /**
     * @see crema.service.MediaLibraryService#readMediaLibrary(java.lang.String)
     */
    public MediaLibrary readMediaLibrary(String libraryName) {
        return null;
    }

}
