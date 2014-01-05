package crema.domain;

import java.util.regex.Pattern;

/**
 * Indicates a display resolution for a video file.
 * 
 * @author Jason Andersen (andersen.jason@gmail.com)
 */
public enum DisplayResolution {
    _480I("480i"),
    _480P("480p"),
    _576I("576i"),
    _576P("576p"),
    _720P("720p"),
    _720I("720i"),
    _1080I("1080i"),
    _1080P("1080p");

    private final String keyword;

    private final Pattern pattern;

    /**
     * Constructor.
     * @param keyword
     */
    DisplayResolution(final String keyword) {
        this.keyword = keyword;
        String regex = String.format("^%s$", keyword);
        pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
    }

    /**
     * @param token
     * @return true if the token matches this resolution
     */
    public boolean matches(final String token) {
        return pattern.matcher(token).matches();
    }

    /**
     * @see java.lang.Enum#toString()
     */
    @Override
    public String toString() {
        return keyword;
    }

}
