package crema.util.text;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

/**
 * Determines if two files are actually two parts of the same media item.
 * 
 * @author Jason Andersen (andersen.jason@gmail.com)
 */
@Component
public class MultiPartFileDetector {

    /**
     * Words that indicate a file is part of multiple files.
     */
    private static final String[] PART_INDICATORS = {
            "part",
            "cd",
            "disc"
    };

    /**
     * Regular expression to find multi-part files in the following format:
     *  Tron-(2007)-part1.avi
     *  Tron-(2007)-part2.avi
     * 
     * The regular expression defines a few groups used to determine if files are multi-part:
     *  1. The file stub: "Tron-(2007)-"
     *  2. The part indicator: "part"
     *  3. Optional separator between part indicator and part number
     *  4. The part number: "2"
     */
    private static final Pattern PATTERN = Pattern.compile(
            "^(.+)\\b(" + getPartIndicatorRegEx() + ")(\\s|-){0,1}(\\d)\\b.+$", Pattern.CASE_INSENSITIVE);

    /**
     * Regex group index to find the file name stub.
     */
    private static final int STUB_GROUP_IDX = 1;

    /**
     * Regex group index to find the part indicator.
     */
    private static final int PART_INDICATOR_GROUP_IDX = 2;

    /**
     * @return a regex alternation pattern identifying words that indicate a multipart file
     */
    public static String getPartIndicatorRegEx() {
        StringBuilder regex = new StringBuilder();
        for (int index = 0; index < PART_INDICATORS.length; index++) {
            regex.append(PART_INDICATORS[index]);
            if (index < PART_INDICATORS.length - 1) {
                regex.append("|");
            }
        }
        return regex.toString();
    }

    /**
     * @param originalFilePath
     * @param siblingFilePath
     * @return true if these two paths represent two parts of a single media item.
     */
    public boolean isMultiPartFile(final String originalFilePath, final String siblingFilePath) {
        /*
         * in order to be considered a multi-part file, all of the following tests must pass:
         *      - the original file matches the regex pattern
         *      - the sibling file matches the regex pattern
         *      - the file name stub of the original matches the sibling
         *      - part indicator ("part", "cd" or "disc") from the original matches the sibling
         */

        Matcher origMatcher = PATTERN.matcher(originalFilePath);
        if (!origMatcher.matches()) {
            return false;
        }

        Matcher siblingMatcher = PATTERN.matcher(siblingFilePath);
        if (!siblingMatcher.matches()) {
            return false;
        }

        String originalFileStub = origMatcher.group(STUB_GROUP_IDX);
        if (!siblingFilePath.startsWith(originalFileStub)) {
            return false;
        }

        String partIndicator = origMatcher.group(PART_INDICATOR_GROUP_IDX);
        return partIndicator.equals(siblingMatcher.group(PART_INDICATOR_GROUP_IDX));
    }

}
