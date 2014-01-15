package crema.domain;

/**
 * A stub of a movie, used when attribute searches are ambiguous and multiple candidates
 * were found for a single movie.
 * 
 * @author Jason Andersen (andersen.jason@gmail.com)
 */
public class MovieStub {

    private final AttributeSource source;

    private final String id;

    private final String title;

    private final int year;

    /**
     * Constructor.
     * @param source
     * @param id
     * @param title
     * @param year
     */
    public MovieStub(final AttributeSource source, final String id, final String title, final int year) {
        this.source = source;
        this.id = id;
        this.title = title;
        this.year = year;
    }

    public AttributeSource getSource() {
        return source;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public int getYear() {
        return year;
    }

}
