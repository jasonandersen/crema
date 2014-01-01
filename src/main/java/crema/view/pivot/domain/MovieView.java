package crema.view.pivot.domain;

import org.apache.commons.lang.Validate;

import crema.domain.HasName;
import crema.domain.MediaLibrary;
import crema.domain.Movie;
import crema.util.text.FileSize;

/**
 * A view wrapper around a {@link Movie} object.
 * 
 * @author Jason Andersen (andersen.jason@gmail.com)
 */
public class MovieView implements HasName {

    private final Movie movie;

    /**
     * Constructor.
     * @param movie
     */
    public MovieView(final Movie movie) {
        Validate.notNull(movie);
        this.movie = movie;
    }

    /**
     * @return name of the movie
     */
    public String getName() {
        return movie.getName();
    }

    /**
     * @return path of the movie file relative to the MediaLibrary
     */
    public String getFirstFileRelativePath() {
        String path = movie.getFirstMediaFile().getRelativePath();
        String numFiles = "";
        if (movie.hasMultipleParts()) {
            numFiles = String.format(" (1st of %d files)", movie.getMediaFiles().size());
        }
        return String.format("%s%s", path, numFiles);
    }

    /**
     * @return the name of the library this movie belongs to
     */
    public String getMediaLibraryName() {
        MediaLibrary library = movie.getMediaLibrary();
        return library.getName();
    }

    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return movie.getName();
    }

    /**
     * @return the size of all media files for this movie in a displayable format
     */
    public String getFileSize() {
        return new FileSize(movie.getTotalSize()).toString();
    }
}
