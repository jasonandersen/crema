package crema.domain;

import java.util.regex.Pattern;

/**
 * Indicates the source of a movie recording.
 * 
 * @author Jason Andersen (andersen.jason@gmail.com)
 */
public enum RecordingSource {
    DVD("DVD", "dvdrip", "dvd", "dvdscr"),
    BLUE_RAY("BlueRay", "bray", "bluray", "blueray", "brrip", "brayrip", "br", "bdrip");

    private final Pattern pattern;

    private final String displayName;

    /**
     * Constructor.
     * @param keywords
     */
    RecordingSource(final String displayName, final String... keywords) {
        this.displayName = displayName;
        //build out regex pattern
        StringBuilder regex = new StringBuilder();
        regex.append("^(");
        int index = 0;
        for (String keyword : keywords) {
            regex.append(keyword);
            if (index < keywords.length - 1) {
                regex.append("|");
            }
        }
        regex.append(")$");
        pattern = Pattern.compile(regex.toString(), Pattern.CASE_INSENSITIVE);
    }

    /**
     * @param token
     * @return true if the token matches this recording source
     */
    public boolean matches(final String token) {
        if (token == null || token.isEmpty()) {
            return false;
        }
        return pattern.matcher(token).matches();
    }

    /**
     * @see java.lang.Enum#toString()
     */
    @Override
    public String toString() {
        return displayName;
    }

}
