package crema.util.text;

import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

/**
 * Strips out the multi part indicators from the movie name tokens.
 * 
 * @author Jason Andersen (andersen.jason@gmail.com)
 */
@Component
public class MultiPartIndicatorDecorator implements TokenDecorator {

    /**
     * Indicates a token that represents both the part indicator and number, like so:
     *      "part01".
     */
    private static final Pattern PART_INDICATOR_NUMBER = Pattern.compile(
            "^(" + MultiPartFileDetector.getPartIndicatorRegEx() + ")0?\\d$", Pattern.CASE_INSENSITIVE);

    /**
     * Indicates a token that represents just the part indicator, like so:
     *      "part".
     */
    private static final Pattern PART_INDICATOR = Pattern.compile(
            "^" + MultiPartFileDetector.getPartIndicatorRegEx() + "$", Pattern.CASE_INSENSITIVE);

    /**
     * Indicates a token that represents just the part number indicator, like so:
     *      "01".
     */
    private static final Pattern PART_NUMBER = Pattern.compile("^0?\\d$");

    /**
     * @see crema.util.text.TokenDecorator#decorate(java.util.List)
     */
    public void decorate(final List<String> tokens) {
        List<Integer> removalIndices = new LinkedList<Integer>();

        for (int index = 0; index < tokens.size(); index++) {
            if (tokenIsPartIndicatorAndPartNumber(tokens.get(index))) {
                removalIndices.add(index);
            } else if (tokensArePartIndicatorAndPartNumber(tokens, index)) {
                removalIndices.add(index);
                removalIndices.add(index + 1);
                index++;
            }
        }
        for (int i = removalIndices.size() - 1; i >= 0; i--) {
            int removalIndex = removalIndices.get(i);
            tokens.remove(removalIndex);
        }
    }

    /**
     * @param tokens
     * @param index
     * @return true if token1 is a part indicator and token2 is a part number
     */
    private boolean tokensArePartIndicatorAndPartNumber(final List<String> tokens, final int index) {
        if (index == tokens.size() - 1) {
            return false;
        }
        String token1 = tokens.get(index);
        String token2 = tokens.get(index + 1);
        return PART_INDICATOR.matcher(token1).matches() && PART_NUMBER.matcher(token2).matches();
    }

    /**
     * @param token
     * @return true if this token represents a part indicator and part number as a single token
     */
    private boolean tokenIsPartIndicatorAndPartNumber(final String token) {
        return PART_INDICATOR_NUMBER.matcher(token).matches();
    }

}
