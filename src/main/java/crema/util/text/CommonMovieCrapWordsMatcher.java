package crema.util.text;

import java.util.Collection;
import java.util.HashSet;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import crema.domain.DisplayResolution;
import crema.domain.RecordingSource;

/**
 * Finds words within a movie title that aren't actually part of the movie name itself
 * but meta data about the movie.
 * 
 * @author Jason Andersen (andersen.jason@gmail.com)
 */
@Service
public class CommonMovieCrapWordsMatcher {

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

    private final Collection<Pattern> miscPatterns;

    @Autowired
    private ReleaseYearMatcher releaseYearMatcher;

    /**
     * Constructor.
     */
    public CommonMovieCrapWordsMatcher() {
        miscPatterns = new HashSet<Pattern>();
        for (String crapPattern : CRAP_PATTERNS) {
            String regex = String.format("^%s$", crapPattern);
            miscPatterns.add(Pattern.compile(regex, Pattern.CASE_INSENSITIVE));
        }
        releaseYearMatcher = new ReleaseYearMatcher();
    }

    /**
     * Determines if the argument is a crap word.
     * @param token
     * @return true if the argument matches a crap word
     */
    public boolean isCrapWord(final String token) {
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
     * @return true if this token indicates a movie release year
     */
    private boolean isReleaseYear(final String token) {
        return releaseYearMatcher.matches(token) != null;
    }

    /**
     * @param token
     * @return true if this token indicates a recording source
     */
    private boolean isRecordingSource(final String token) {
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
    private boolean isDisplayResolution(final String token) {
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
    private boolean isMiscCrapWord(final String token) {
        for (Pattern pattern : miscPatterns) {
            if (pattern.matcher(token).matches()) {
                return true;
            }
        }
        return false;
    }

}
