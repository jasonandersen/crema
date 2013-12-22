package crema.service.impl;

import java.io.File;
import java.util.Iterator;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.Validate;
import org.springframework.stereotype.Service;

import crema.domain.MediaLibrary;
import crema.exception.MediaFileException;
import crema.service.MovieFileDiscoveryService;

/**
 * Implementation of the {@link MovieFileDiscoverService} implementation.
 * 
 * @author Jason Andersen (andersen.jason@gmail.com)
 */
@Service
public class MovieFileDirectoryServiceImpl implements MovieFileDiscoveryService {

    private static final String[] MOVIE_EXTENSIONS = {
            "avi",
            "asf",
            "flv",
            "m4v",
            "mkv",
            "mov",
            "mpeg",
            "mpg",
            "mpe",
            "wmv",
    };

    /**
     * @throws MediaFileException 
     * @see crema.service.MovieFileDiscoveryService#discoverMovies(crema.domain.MediaLibrary)
     */
    public void discoverMovies(final MediaLibrary library) throws MediaFileException {
        Validate.notNull(library);
        Validate.notNull(library.getBaseDirectory());

        discover(library);
    }

    /**
     * Discover movie files within a media library base directory.
     * @param library
     * @param directory
     * @throws MediaFileException 
     */
    private void discover(final MediaLibrary library) throws MediaFileException {
        Iterator<File> files = FileUtils.iterateFiles(library.getBaseDirectory(), MOVIE_EXTENSIONS, true);
        while (files.hasNext()) {
            library.addMovieFile(files.next());
        }
    }

}
