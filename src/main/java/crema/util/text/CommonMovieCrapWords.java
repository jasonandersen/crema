package crema.util.text;

import java.util.Collection;
import java.util.HashSet;
import java.util.regex.Pattern;

/**
 * List of crap words that often appear in movie file names that aren't
 * part of the title itself.
 * 
 * @author Jason Andersen (andersen.jason@gmail.com)
 */
public class CommonMovieCrapWords {

    /**
     * List of commonly found crap words in movie file names.
     */
    private static final String[] CRAP_PATTERNS = new String[] {
            "dvdrip",
            "dvd",
            "dvdscr",
            "brayrip",
            "brrip",
            "bray",
            "blueray",
            "bluray",
            "br",
            "bdrip",
            "AC3",
            "AAC",
            "xvid",
            "\\d\\dfps", /* frames per second */
            "(19|20)\\d\\d", /* year */
            "\\((19|20)\\d\\d\\)", /* year */
            "\\[(19|20)\\d\\d\\]", /* year */
            "\\{(19|20)\\d\\d\\}", /* year */
            "x264",
            "sd",
            "hd",
            "480i",
            "576i",
            "576p",
            "480p",
            "720p",
            "1080i",
            "1080p",
            "\\[.*\\]" /* anything surrounded in brackets is suspect */
    };

    //TODO refactor to use ReleaseYearMatcher, RecordingSource and DisplayResolution

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
