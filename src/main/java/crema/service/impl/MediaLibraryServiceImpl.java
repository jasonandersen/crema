package crema.service.impl;

import java.io.File;
import java.util.List;

import org.apache.commons.lang.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import crema.dao.MediaLibraryDAO;
import crema.domain.MediaLibrary;
import crema.exception.CremaException;
import crema.exception.DuplicateMediaLibraryException;
import crema.exception.InvalidMediaLibraryDirectoryException;
import crema.service.MediaLibraryService;
import crema.service.MovieFileDiscoveryService;

/**
 * Implementation of the {@link MediaLibraryService} service.
 * 
 * @author Jason Andersen (andersen.jason@gmail.com)
 */
@Service
public class MediaLibraryServiceImpl implements MediaLibraryService {

    @Autowired
    private MediaLibraryDAO mediaLibraryDao;

    @Autowired
    private MovieFileDiscoveryService movieFileDiscoverService;

    /**
     * @throws CremaException 
     * @see crema.service.MediaLibraryService#createMediaLibrary(java.io.File, java.lang.String)
     */
    public MediaLibrary createMediaLibrary(final File path, final String name) throws CremaException {
        Validate.notNull(path);
        Validate.notNull(name);

        MediaLibrary library = buildMediaLibrary(path, name);
        validateMediaLibrary(library);
        discoverMovieFiles(library);
        saveMediaLibrary(library);

        return library;
    }

    /**
     * @see crema.service.MediaLibraryService#getAllMediaLibraries()
     */
    public List<MediaLibrary> getAllMediaLibraries() {
        return mediaLibraryDao.readAll();
    }

    /**
     * @see crema.service.MediaLibraryService#readMediaLibrary(java.lang.String)
     */
    public MediaLibrary readMediaLibrary(final String libraryName) {
        return mediaLibraryDao.read(libraryName);
    }

    /**
     * @throws DuplicateMediaLibraryException 
     * @see crema.service.MediaLibraryService#updateMediaLibrary(crema.domain.MediaLibrary)
     */
    public void updateMediaLibrary(final MediaLibrary library) throws DuplicateMediaLibraryException {
        saveMediaLibrary(library);
    }

    /**
     * Builds out a single media library based on name and directory.
     * @param path
     * @param name
     * @return
     */
    private MediaLibrary buildMediaLibrary(final File path, final String name) {
        MediaLibrary library = new MediaLibrary();
        library.setBaseDirectory(path);
        library.setName(name);
        return library;
    }

    /**
     * Validates the {@link MediaLibrary} is valid.
     * @param library
     * @throws InvalidMediaLibraryDirectoryException 
     */
    private void validateMediaLibrary(final MediaLibrary library) throws InvalidMediaLibraryDirectoryException {
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

    /**
     * Discover any movie files that exist within the library.
     * @param library
     * @throws CremaException 
     */
    private void discoverMovieFiles(final MediaLibrary library) throws CremaException {
        movieFileDiscoverService.discoverMovies(library);
    }

    /**
     * @param library
     * @throws DuplicateMediaLibraryException 
     */
    private void saveMediaLibrary(final MediaLibrary library) throws DuplicateMediaLibraryException {
        mediaLibraryDao.save(library);
    }
}
