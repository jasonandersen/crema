package crema.domain;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.Validate;

import crema.exception.MediaFileException;

/**
 * A library housing media files.
 * 
 * @author Jason Andersen (andersen.jason@gmail.com)
 */
public class MediaLibrary {

    public static final String FIELD_NAME = "name";

    public static final String FIELD_BASE_DIR = "baseDirectory";

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
     * Adds a movie file to this library.
     * @param file
     * @throws MediaFileException 
     */
    public void addMovieFile(final File... files) throws MediaFileException {
        Validate.notNull(files);
        Movie movie = new Movie();
        for (File file : files) {
            MediaFile mediaFile = new MediaFile(this, file);
            movie.addFile(mediaFile);
        }

        movies.put(movie.getRelativeFilePath(), movie);
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
     * Testing method to set the movies map to facilitate easier testing.
     * @param movies
     */
    protected void setMovies(final Map<String, Movie> movies) {
        this.movies = movies;
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
