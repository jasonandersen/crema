package crema.domain;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.Validate;

import crema.exception.CremaException;

/**
 * A library housing media files.
 * 
 * @author Jason Andersen (andersen.jason@gmail.com)
 */
public class MediaLibrary {

    public static final String FIELD_NAME = "name";

    public static final String FIELD_BASE_DIR = "baseDirectory";

    private transient Set<MediaLibraryNewMovieListener> newMovieListeners;

    private File baseDirectory;

    private String name;

    /**
     * Map of movies keyed off of the file's path relative to the base directory
     * of this media library.
     */
    private Map<String, Movie> movies;

    /**
     * Constructor.
     */
    public MediaLibrary() {
        movies = new HashMap<String, Movie>();
        newMovieListeners = new HashSet<MediaLibraryNewMovieListener>();
    }

    /**
    * @see java.lang.Object#toString()
    */
    @Override
    public String toString() {
        return name;
    }

    /**
     * @param fileName
     * @return true if this file exists within this media library
     */
    public boolean containsFile(final String fileName) {
        for (Movie movie : movies.values()) {
            for (MediaFile file : movie.getMediaFiles()) {
                if (fileName.equals(file.getFileName())) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Overloaded method to allow a movie to be added by one file.
     * @param file
     * @throws CremaException 
     */
    public void addMovie(final File file) throws CremaException {
        List<File> fileList = new LinkedList<File>();
        fileList.add(file);
        addMovie(fileList);
    }

    /**
     * Adds a movie file to this library.
     * @param file
     * @throws CremaException 
     */
    public void addMovie(final List<File> files) throws CremaException {
        Validate.notNull(files);
        Movie movie = new Movie();
        for (File file : files) {
            MediaFile mediaFile = new MediaFile(this, file);
            movie.addFile(mediaFile);
        }

        movies.put(movie.getRelativeFilePath(), movie);
        notifyListenersNewMovie(movie);
    }

    /**
     * @return an unmodifiable list of movies
     */
    public List<Movie> getMovies() {
        return Collections.unmodifiableList(new ArrayList<Movie>(movies.values()));
    }

    /**
     * @return an unmodifiable map of movies within this library keyed off movie file path
     *      relative to the base directory of this media library.
     */
    public Map<String, Movie> getMoviesByFilePath() {
        return Collections.unmodifiableMap(movies);
    }

    /**
     * @param string
     * @return a collection of movies that match the name of the movie - will never return null
     *      but will return an empty collection if no movies are found.
     */
    public Collection<Movie> getMovies(final String movieName) {
        Collection<Movie> result = new HashSet<Movie>();
        for (Movie movie : movies.values()) {
            if (movie.getName().equals(movieName)) {
                result.add(movie);
            }
        }
        return result;
    }

    /**
     * Registers a new listener to this media library.
     * @param listener
     */
    public void addNewMovieListener(final MediaLibraryNewMovieListener listener) {
        newMovieListeners.add(listener);
    }

    /**
     * Testing method to set the movies map to facilitate easier testing.
     * @param movies
     */
    protected void setMovies(final Map<String, Movie> movies) {
        this.movies = movies;
    }

    /**
     * Notifies listeners that a new movie has been added.
     * @param movie
     * @throws CremaException 
     */
    private void notifyListenersNewMovie(final Movie movie) throws CremaException {
        for (MediaLibraryNewMovieListener listener : newMovieListeners) {
            listener.movieAdded(movie);
        }
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public File getBaseDirectory() {
        return baseDirectory;
    }

    public void setBaseDirectory(final File baseDirectory) {
        this.baseDirectory = baseDirectory;
    }

}
