package crema.domain;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        return String.format("[MediaLibrary]name=%s;baseDirectory=%s", name, baseDirectory);
    }

    /**
     * @param fileName
     * @return true if this file exists within this media library
     */
    public boolean containsFile(final String fileName) {
        for (Movie movie : movies.values()) {
            MediaFile file = movie.getMediaFile();
            if (fileName.equals(file.getFileName())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Adds a movie file to this library.
     * @param file
     * @throws MediaFileException 
     */
    public void addMovieFile(final File file) throws MediaFileException {
        MediaFile mediaFile = new MediaFile(this, file);
        Movie movie = new Movie();
        movie.setMediaFile(mediaFile);

        movies.put(mediaFile.getRelativePath(), movie);
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
