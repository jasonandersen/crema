package crema.service.impl;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import crema.domain.MediaLibrary;
import crema.domain.Movie;
import crema.exception.MediaFileException;
import crema.service.MovieFileDiscoveryService;
import crema.service.MovieNameService;
import crema.util.PathUtils;

/**
 * Implementation of the {@link MovieFileDiscoveryService} implementation.
 * 
 * @author Jason Andersen (andersen.jason@gmail.com)
 */
@Service
public class MovieFileDiscoveryServiceImpl implements MovieFileDiscoveryService {

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

    private List<String> extensions;

    @Autowired
    private MovieNameService movieNameService;

    /**
     * Constructor.
     */
    public MovieFileDiscoveryServiceImpl() {
        extensions = Arrays.asList(MOVIE_EXTENSIONS);
    }

    /**
     * @throws MediaFileException 
     * @see crema.service.MovieFileDiscoveryService#discoverMovies(crema.domain.MediaLibrary)
     */
    public void discoverMovies(final MediaLibrary library) throws MediaFileException {
        Validate.notNull(library);
        Validate.notNull(library.getBaseDirectory());
        discover(library, library.getBaseDirectory());
        injectMovieNames(library);
    }

    /**
     * Injects names of the movies into the library.
     * @param library
     */
    private void injectMovieNames(final MediaLibrary library) {
        for (Movie movie : library.getMovies()) {
            movieNameService.guessName(movie);
        }
    }

    /**
     * Discover movie files within a media library base directory.
     * @param library
     * @param directory
     * @throws MediaFileException 
     */
    private void discover(final MediaLibrary library, final File directory) throws MediaFileException {
        Validate.notNull(directory, "Directory cannot be null!");
        Validate.isTrue(directory.isDirectory(), "File must be a directory!");
        for (File child : directory.listFiles()) {
            if (child.isFile()) {
                inspectFile(library, child);
            } else {
                discover(library, child);
            }
        }
    }

    /**
     * Inspects a single file object to determine if it is a movie file.
     * @param library
     * @param file
     * @throws MediaFileException 
     */
    private void inspectFile(final MediaLibrary library, final File file) throws MediaFileException {
        if (isMovie(file)) {
            library.addMovieFile(file);
        }
    }

    /**
     * @param file
     * @return true if this file represents a movie file.
     */
    private boolean isMovie(final File file) {
        String extension = PathUtils.getFileExtension(file);
        extension = extension.toLowerCase();
        return extensions.contains(extension);
    }
}
