package crema.service.impl;

import java.io.File;

import org.apache.commons.lang.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import crema.dao.MediaLibraryDAO;
import crema.domain.MediaLibrary;
import crema.exception.InvalidMediaLibraryDirectoryException;
import crema.exception.MediaLibraryException;
import crema.service.MediaLibraryService;

/**
 * Implementation of the {@link MediaLibraryService} service.
 * 
 * @author Jason Andersen (andersen.jason@gmail.com)
 */
@Service
public class MediaLibraryServiceImpl implements MediaLibraryService {

    @Autowired
    private MediaLibraryDAO mediaLibraryDao;

    /**
     * @see crema.service.MediaLibraryService#createMediaLibrary(java.io.File, java.lang.String)
     */
    public MediaLibrary createMediaLibrary(File path, String name) throws MediaLibraryException {
        Validate.notNull(path);
        Validate.notNull(name);

        MediaLibrary library = new MediaLibrary();
        library.setBaseDirectory(path);
        library.setName(name);
        validateMediaLibrary(library);
        mediaLibraryDao.save(library);

        return library;
    }

    /**
     * @see crema.service.MediaLibraryService#readMediaLibrary(java.lang.String)
     */
    public MediaLibrary readMediaLibrary(String libraryName) {
        return mediaLibraryDao.read(libraryName);
    }

    /**
     * Validates the {@link MediaLibrary} is valid.
     * @param library
     * @throws InvalidMediaLibraryDirectoryException 
     */
    private void validateMediaLibrary(MediaLibrary library) throws InvalidMediaLibraryDirectoryException {
        File directory = library.getBaseDirectory();
        if (!directory.exists()) {
            throw new InvalidMediaLibraryDirectoryException(String.format("the directory %s does not exist.",
                    library.getBaseDirectory()));
        }
        if (!directory.canRead()) {
            throw new InvalidMediaLibraryDirectoryException(String.format("the directory %s cannot be read.",
                    library.getBaseDirectory()));
        }
    }
}
