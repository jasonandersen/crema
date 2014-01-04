package crema.domain;

import org.apache.commons.lang.Validate;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * A single attribute of a media item.
 * 
 * @author Jason Andersen (andersen.jason@gmail.com)
 */
public class Attribute implements Comparable<Attribute> {

    private static final int HASH_CODE_PRIME1 = 383;
    private static final int HASH_CODE_PRIME2 = 313;

    private static final String TO_STRING_FORMAT = "[%s]%s=%s";

    private final AttributeType type;

    private final AttributeSource source;

    private final Object value;

    /**
     * Constructor.
     * @param type
     * @param source
     * @param value
     */
    public Attribute(final AttributeType type, final AttributeSource source, final Object value) {
        Validate.notNull(type);
        Validate.notNull(source);
        Validate.notNull(value);
        this.type = type;
        this.source = source;
        this.value = value;
    }

    /**
     * @see java.lang.Comparable#compareTo(java.lang.Object)
     */
    public int compareTo(final Attribute that) {
        if (that == null) {
            return -1;
        }
        //compare sources first
        Integer thisSourceOrdinal = this.getSource().ordinal();
        Integer thatSourceOrdinal = that.getSource().ordinal();
        Integer sourceOrdinalCompare = thisSourceOrdinal.compareTo(thatSourceOrdinal);
        if (sourceOrdinalCompare != 0) {
            return sourceOrdinalCompare;
        }
        //compare types next
        Integer thisTypeOrdinal = this.getType().ordinal();
        Integer thatTypeOrdinal = that.getType().ordinal();
        Integer typeOrdinalCompare = thisTypeOrdinal.compareTo(thatTypeOrdinal);
        if (typeOrdinalCompare != 0) {
            return typeOrdinalCompare;
        }
        //compare values next
        String thisValue = this.getValue().toString();
        String thatValue = that.getValue().toString();
        return thisValue.compareTo(thatValue);
    }

    /**
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(final Object other) {
        if (other == null) {
            return false;
        }
        if (!(other instanceof Attribute)) {
            return false;
        }

        Attribute that = (Attribute) other;
        EqualsBuilder builder = new EqualsBuilder();
        builder.append(this.getSource(), that.getSource());
        builder.append(this.getType(), that.getType());
        builder.append(this.getValue(), that.getValue());
        return builder.isEquals();
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        HashCodeBuilder builder = new HashCodeBuilder(HASH_CODE_PRIME1, HASH_CODE_PRIME2);
        builder.append(getSource());
        builder.append(getType());
        builder.append(getValue());
        return builder.toHashCode();
    }

    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return String.format(TO_STRING_FORMAT, source, type, value);
    }

    public AttributeType getType() {
        return type;
    }

    public AttributeSource getSource() {
        return source;
    }

    public Object getValue() {
        return value;
    }

}
