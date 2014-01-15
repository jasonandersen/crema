package crema.domain;

import org.apache.commons.lang.Validate;

/**
 * Base class to indicate the result of an attempt to discover movie attributes.
 * 
 * @author Jason Andersen (andersen.jason@gmail.com)
 */
public abstract class AttributesResult {

    private static final String TO_STRING_FORMAT = "[%s] %s %s";

    /**
     * Indicates the status of this result.
     */
    public enum Status {
        SUCCESSFUL,
        AMBIGUOUS_RESULTS_FOUND,
        NO_RESULTS_FOUND,
        EXCEPTION;
    }

    private final Status status;

    private final AttributeSource source;

    private final Object result;

    /**
     * Constructor.
     * @param status
     * @param source
     * @param result
     * @throws IllegalArgumentException when status is anything other than NO_RESULTS_FOUND and result is null
     */
    protected AttributesResult(final Status status, final AttributeSource source, final Object result) {
        Validate.notNull(source);
        Validate.notNull(status);
        if (status != Status.NO_RESULTS_FOUND && result == null) {
            throw new IllegalArgumentException("result cannot be null when status is " + status);
        }
        this.status = status;
        this.source = source;
        this.result = result;
    }

    /**
     * @return indicates what happened with this result
     */
    public Status getStatus() {
        return status;
    }

    /**
     * @return the source of this attributes result
     */
    public AttributeSource getSource() {
        return source;
    }

    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return String.format(TO_STRING_FORMAT, source, status, result);
    }

    /**
     * @return the actual result, type will vary depending on what happened - will only return 
     *      null when status == NO_RESULTS_FOUND
     */
    protected Object getResult() {
        return result;
    }

}
