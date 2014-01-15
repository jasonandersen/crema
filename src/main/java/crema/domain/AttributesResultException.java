package crema.domain;

import org.apache.commons.lang.Validate;

/**
 * Indicates that some sort of exception happened during retrieval of the attributes.
 * 
 * @author Jason Andersen (andersen.jason@gmail.com)
 */
public class AttributesResultException extends AttributesResult {

    /**
     * Constructor.
     * @param source
     * @param exception
     */
    public AttributesResultException(final AttributeSource source, final Exception exception) {
        super(AttributesResult.Status.EXCEPTION, source, exception);
        Validate.notNull(exception, "Exception cannot be null");
    }

    /**
     * @return the exception object caught during the search for attributes
     */
    public Exception getException() {
        return (Exception) getResult();
    }
}
