package crema.domain;

import java.util.Comparator;

/**
 * Compares two nameable objects by name.
 * 
 * @author Jason Andersen (andersen.jason@gmail.com)
 */
public class HasNameComparator implements Comparator<HasName> {

    /**
     * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
     */
    public int compare(final HasName hasName1, final HasName hasName2) {
        if (hasName1 == null && hasName2 == null) {
            return 0;
        }
        if (hasName1 == null) {
            return -1;
        }
        if (hasName2 == null) {
            return 1;
        }
        return hasName1.getName().compareTo(hasName2.getName());
    }

}
