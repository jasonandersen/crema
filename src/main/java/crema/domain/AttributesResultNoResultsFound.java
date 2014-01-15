package crema.domain;

/**
 * Indicates that a search for attributes was successfully executed but no
 * results were found.
 * 
 * @author Jason Andersen (andersen.jason@gmail.com)
 */
public class AttributesResultNoResultsFound extends AttributesResult {

    public AttributesResultNoResultsFound(final AttributeSource source) {
        super(AttributesResult.Status.NO_RESULTS_FOUND, source, null);
    }
}
