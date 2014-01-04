package crema.domain;

/**
 * Indicates the type of {@link Attribute}.
 * 
 * @author Jason Andersen (andersen.jason@gmail.com)
 */
public enum AttributeType {

    /*
     * NOTE: the order the enum elements are defined in define their order of importance. Changing
     * the order of these elements will change sort orders for Attribute collections.
     */

    RELEASE_YEAR,
    DISPLAY_RESOLUTION,
    RECORDING_SOURCE

    /*
     * possible members:
     *  +isValid()
     *  +isSingular()
     *  +isComposite()
     *  
     *  -priority
     */

}
