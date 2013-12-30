package crema.service.impl;

import java.io.File;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import org.apache.commons.lang.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import crema.domain.MediaLibrary;
import crema.domain.Movie;
import crema.exception.MediaFileException;
import crema.service.MovieFileDiscoveryService;
import crema.service.MovieNameService;
import crema.util.PathUtils;
import crema.util.text.MultiPartFileDetector;

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

    @Autowired
    private MultiPartFileDetector multiPartDetector;

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
        Queue<File> directoryChildren = sortChildrenByPath(directory);
        while (!directoryChildren.isEmpty()) {
            File child = directoryChildren.poll();
            if (child.isDirectory()) {
                discover(library, child);
            } else {
                inspectFile(library, child, directoryChildren);
            }
        }
    }

    /**
     * @param directory
     * @return a list of files sorted by path
     */
    private Queue<File> sortChildrenByPath(final File directory) {
        LinkedList<File> files = new LinkedList<File>();
        files.addAll(Arrays.asList(directory.listFiles()));
        Collections.sort(files, new FileByPathComparator());
        return files;
    }

    /**
     * Inspects a single file object to determine if it is a movie file.
     * @param library
     * @param file
     * @throws MediaFileException 
     */
    private void inspectFile(final MediaLibrary library, final File file, final Queue<File> siblingFiles)
            throws MediaFileException {
        if (!isMovie(file)) {
            return;
        }
        List<File> mediaItem = new LinkedList<File>();
        mediaItem.add(file);
        while (isMultiPartFile(file, siblingFiles.peek())) {
            mediaItem.add(siblingFiles.poll());
        }
        library.addMovie(mediaItem);
    }

    /**
     * @param originalFile
     * @param siblingFile
     * @return true if the original file and the sibling file represent different parts of the same media item
     */
    private boolean isMultiPartFile(final File originalFile, final File siblingFile) {
        Validate.notNull(originalFile);
        if (siblingFile == null) {
            return false;
        }
        String originalFilePath = originalFile.getPath();
        String siblingFilePath = siblingFile.getPath();
        return multiPartDetector.isMultiPartFile(originalFilePath, siblingFilePath);
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

    /**
     * Sorts {@link File} objects by path.
     */
    private class FileByPathComparator implements Comparator<File> {
        /**
         * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
         */
        public int compare(final File file1, final File file2) {
            Validate.notNull(file1);
            Validate.notNull(file2);
            return file1.getPath().compareTo(file2.getPath());
        }
    }
}
