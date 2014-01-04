package crema.domain;

import java.util.Iterator;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * A collection of attributes for a single media item.
 * 
 * @author Jason Andersen (andersen.jason@gmail.com)
 */
public class Attributes implements Iterable<Attribute> {

    private final SortedSet<Attribute> attributes;

    /**
     * Constructor.
     */
    public Attributes() {
        attributes = new TreeSet<Attribute>();
    }

    /**
     * @see java.lang.Iterable#iterator()
     */
    public Iterator<Attribute> iterator() {
        return attributes.iterator();
    }

    /**
     * @param type
     * @return an attribute of the specified type, if multiple attributes have the same type will return the 
     *      first one found based on {@link Attribute} class' natural sorting order, if no attributes exist
     *      of the specified type will return null
     */
    public Attribute getAttribute(final AttributeType type) {
        if (type == null) {
            return null;
        }
        for (Attribute attribute : attributes) {
            if (type.equals(attribute.getType())) {
                return attribute;
            }
        }
        return null;
    }

    /**
     * Adds a single attribute.
     * @param attribute
     */
    public void addAttribute(final Attribute attribute) {
        if (attribute == null) {
            return;
        }
        attributes.add(attribute);
    }

    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        int index = 0;
        Iterator<Attribute> iterator = attributes.iterator();
        while (iterator.hasNext()) {
            builder.append(iterator.next());
            if (index < attributes.size() - 1) {
                builder.append(", ");
            }
        }
        return builder.toString();
    }

}
