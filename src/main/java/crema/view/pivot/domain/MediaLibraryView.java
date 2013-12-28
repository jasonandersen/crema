package crema.view.pivot.domain;

import java.util.Collections;

import org.apache.commons.lang.Validate;
import org.apache.pivot.collections.LinkedList;
import org.apache.pivot.collections.List;

import crema.domain.MediaLibrary;
import crema.domain.Movie;
import crema.domain.MovieNameComparator;

/**
 * A view wrapper around {@link MediaLibrary} objects.
 * 
 * @author Jason Andersen (andersen.jason@gmail.com)
 */
public class MediaLibraryView {

    private final MediaLibrary library;

    /**
     * Constructor.
     * @param library
     */
    public MediaLibraryView(final MediaLibrary library) {
        Validate.notNull(library);
        this.library = library;
    }

    /**
     * @return name of the media library
     */
    public String getName() {
        return library.getName();
    }

    /**
     * @param updateText
     */
    public void setName(final String name) {
        library.setName(name);
    }

    /**
     * @return a Pivot list of movies in this media library sorted by name
     */
    public List<MovieView> getMovies() {
        java.util.List<Movie> moviesList = new java.util.LinkedList<Movie>(library.getMovies());
        Collections.sort(moviesList, new MovieNameComparator());
        LinkedList<MovieView> out = new LinkedList<MovieView>();
        for (Movie movie : moviesList) {
            out.add(new MovieView(movie));
        }
        return out;
    }

    /**
     * @return the media library, will never return null
     */
    public MediaLibrary getMediaLibrary() {
        return library;
    }

    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return library.getName();
    }

}
