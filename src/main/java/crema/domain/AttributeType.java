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
    GENRES,
    SYNOPSIS,
    MPAA_RATING,
    RUNTIME,
    CRITICS_CONSENSUS,
    CRITICS_SCORE,
    AUDIENCE_SCORE,
    IMAGES,

    MOVIE_ID,
    DISPLAY_RESOLUTION,
    RECORDING_SOURCE,

    /*
     * possible members:
     *  +isValid(Object value)
     *  +getPlurality()
     *  +isVisible()
     */

}
