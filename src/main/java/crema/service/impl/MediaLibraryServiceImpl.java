package crema.service.impl;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import crema.dao.MediaLibraryDAO;
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

    @Autowired
    private MediaLibraryDAO mediaLibraryDao;

    /**
     * @see crema.service.MediaLibraryService#createMediaLibrary(java.io.File, java.lang.String)
     */
    public MediaLibrary createMediaLibrary(File path, String name) throws MediaLibraryException {
        validateDirectory(path);
        MediaLibrary library = new MediaLibrary();
        library.setBaseDirectory(path);
        library.setName(name);
        return mediaLibraryDao.save(library);
    }

    /**
     * @see crema.service.MediaLibraryService#readMediaLibrary(java.lang.String)
     */
    public MediaLibrary readMediaLibrary(String libraryName) {
        return null;
    }

    /**
     * @param path
     */
    private void validateDirectory(File path) {
        //TODO we should definitely do something here
    }

}
