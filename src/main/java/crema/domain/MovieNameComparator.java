package crema.domain;

import java.util.Comparator;

/**
 * Compares two movies by name.
 * 
 * @author Jason Andersen (andersen.jason@gmail.com)
 */
public class MovieNameComparator implements Comparator<Movie> {

    /**
     * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
     */
    public int compare(final Movie movie1, final Movie movie2) {
        if (movie1 == null && movie2 == null) {
            return 0;
        }
        if (movie1 == null) {
            return -1;
        }
        if (movie2 == null) {
            return 1;
        }
        return movie1.getName().compareTo(movie2.getName());
    }

}
