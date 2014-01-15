package crema.domain;

import java.util.List;

import org.apache.commons.lang.Validate;

/**
 * Indicates that several movies came back from an attributes search and we 
 * cannot determine which movie was the matching movie.
 * 
 * @author Jason Andersen (andersen.jason@gmail.com)
 */
public class AttributesResultAmbiguous extends AttributesResult {

    /**
     * Constructor.
     * @param status
     * @param source
     * @param result
     */
    public AttributesResultAmbiguous(final AttributeSource source, final List<MovieStub> stubs) {
        super(AttributesResult.Status.AMBIGUOUS_RESULTS_FOUND, source, stubs);
        Validate.notNull(stubs);
        Validate.isTrue(!stubs.isEmpty());
    }

    /**
     * @return a list of movie stubs representing the movies that were found during the search
     *      for movie attributes.
     */
    @SuppressWarnings("unchecked")
    public List<MovieStub> getMovieStubs() {
        return (List<MovieStub>) getResult();
    }

}
