package crema.domain;

import java.util.List;

import org.apache.commons.lang.Validate;

/**
 * Indicates that attributes were successfully retrieved.
 * 
 * @author Jason Andersen (andersen.jason@gmail.com)
 */
public class AttributesResultSuccessful extends AttributesResult {

    /**
     * Constructor.
     * @param source
     * @param attributes
     */
    public AttributesResultSuccessful(final AttributeSource source, final List<Attribute> attributes) {
        super(AttributesResult.Status.SUCCESSFUL, source, attributes);
        Validate.notNull(attributes, "Attributes cannot be null");
        Validate.isTrue(!attributes.isEmpty(), "Attributes cannot be empty");
    }

    /**
     * @return the list of attributes discovered, will never return null and will never return a non-empty list
     */
    @SuppressWarnings("unchecked")
    public List<Attribute> getAttributes() {
        return (List<Attribute>) getResult();
    }

}
