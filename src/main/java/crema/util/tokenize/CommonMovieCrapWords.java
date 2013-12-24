package crema.util.tokenize;

import java.util.Collection;
import java.util.HashSet;
import java.util.regex.Pattern;

/**
 * List of crap words that often appear in movie file names.
 * 
 * @author Jason Andersen (andersen.jason@gmail.com)
 */
public class CommonMovieCrapWords {

    private static final String[] CRAP_PATTERNS = new String[] {
            "dvdrip",
            "brayrip",
            "brrip",
            "(19|20)\\d\\d",
            "\\((19|20)\\d\\d\\)", /* year */
            "\\[(19|20)\\d\\d\\]", /* year */
            "x264",
            "sd",
            "hd",
            "480i",
            "576i",
            "480p",
            "720p",
            "1080i",
            "1080p",
            "\\[.*\\]"
    };

    private static final Collection<Pattern> PATTERNS;

    /**
     * Compile all the regex patterns
     */
    static {
        PATTERNS = new HashSet<Pattern>();
        for (String crapPattern : CRAP_PATTERNS) {
            String regex = String.format("^%s$", crapPattern);
            PATTERNS.add(Pattern.compile(regex, Pattern.CASE_INSENSITIVE));
        }
    }

    /**
     * Private constructor - no instantiation!
     */
    private CommonMovieCrapWords() {
        //no instantiation for you!
    }

    /**
     * Determines if the argument is a crap word.
     * @param token
     * @return true if the argument matches a crap word
     */
    public static boolean isCrapWord(final String token) {
        for (Pattern pattern : PATTERNS) {
            if (pattern.matcher(token).matches()) {
                return true;
            }
        }
        return false;
    }

}
