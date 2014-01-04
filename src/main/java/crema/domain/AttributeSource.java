package crema.domain;

/**
 * The source an {@link Attribute} came from.
 * 
 * @author Jason Andersen (andersen.jason@gmail.com)
 */
public enum AttributeSource {

    /*
     * NOTE: the order the enum elements are defined in define their order of importance. Changing
     * the order of these elements will change sort orders for Attribute collections.
     */

    /**
     * Uses the Rotten Tomatoes REST API to gather attributes about a movie.
     */
    ROTTEN_TOMATOES,
    /**
     * Use some common terms that show up in file names to infer attributes
     * about a movie.
     */
    FILE_NAME

    /*
     * possible members:
     *  -priority
     *  -description
     *  -displayName
     */
}
