package crema.util.text;

import java.util.Collection;
import java.util.HashSet;
import java.util.regex.Pattern;

import crema.domain.DisplayResolution;
import crema.domain.RecordingSource;
import crema.util.BeanContext;

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
            "AC3",
            "AAC",
            "xvid",
            "\\d\\dfps", /* frames per second */
            "x264",
            "sd",
            "hd",
            "\\[.*\\]" /* anything surrounded in brackets is suspect */
    };

    //TODO refactor to use ReleaseYearMatcher, RecordingSource and DisplayResolution

    private static final Collection<Pattern> PATTERNS;

    private static ReleaseYearMatcher releaseYearMatcher;

    /**
     * Compile all the regex patterns
     */
    static {
        PATTERNS = new HashSet<Pattern>();
        for (String crapPattern : CRAP_PATTERNS) {
            String regex = String.format("^%s$", crapPattern);
            PATTERNS.add(Pattern.compile(regex, Pattern.CASE_INSENSITIVE));
        }
        releaseYearMatcher = BeanContext.getBean(ReleaseYearMatcher.class);
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
        if (isReleaseYear(token)) {
            return true;
        }
        if (isRecordingSource(token)) {
            return true;
        }
        if (isDisplayResolution(token)) {
            return true;
        }
        if (isMiscCrapWord(token)) {
            return true;
        }
        return false;
    }

    /**
     * @param token
     * @return
     */
    private static boolean isReleaseYear(final String token) {
        return releaseYearMatcher.matches(token) != null;
    }

    /**
     * @param token
     * @return true if this token indicates a recording source
     */
    private static boolean isRecordingSource(final String token) {
        for (RecordingSource source : RecordingSource.values()) {
            if (source.matches(token)) {
                return true;
            }
        }
        return false;
    }

    /**
     * @param token
     * @return true if this token indicates a display resolution
     */
    private static boolean isDisplayResolution(final String token) {
        for (DisplayResolution resolution : DisplayResolution.values()) {
            if (resolution.matches(token)) {
                return true;
            }
        }
        return false;
    }

    /**
     * @param token
     * @return true if this token is one of the miscellaneous crap words defined earlier
     */
    private static boolean isMiscCrapWord(final String token) {
        for (Pattern pattern : PATTERNS) {
            if (pattern.matcher(token).matches()) {
                return true;
            }
        }
        return false;
    }

}
