package crema.util.text;

import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

/**
 * Matches tokens to determine if they represent a media item's release year.
 * 
 * @author Jason Andersen (andersen.jason@gmail.com)
 */
@Service
public class ReleaseYearMatcher {

    private static final String RELEASE_YEAR_REGEX = "^(\\(|\\[|\\{)?(19\\d\\d|20(0|1|2)\\d)(\\)|\\]|\\})?$";

    private static final String CLEAN_TOKEN_REGEX = "\\(|\\)|\\{|\\}|\\[|\\]";

    private static final Pattern PATTERN;

    static {
        PATTERN = Pattern.compile(RELEASE_YEAR_REGEX);
    }

    /**
     * @param token
     * @return a cleaned up token containing release year, will return null if the token does not contain
     *      a release year
     */
    public String matches(final String token) {
        if (PATTERN.matcher(token).matches()) {
            return cleanToken(token);
        }
        return null;
    }

    /**
     * @param token
     * @return
     */
    private String cleanToken(final String token) {
        return token.replaceAll(CLEAN_TOKEN_REGEX, "");
    }

}
