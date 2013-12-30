package crema.util.text;

import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * 
 * @author Jason Andersen (andersen.jason@gmail.com)
 */
public class MultiPartIndicatorDecorator implements TokenDecorator {

    private static final Pattern PART_INDICATOR_NUMBER = Pattern.compile(
            "^(" + MultiPartFileDetector.getPartIndicatorRegEx() + ")\\d$", Pattern.CASE_INSENSITIVE);

    private static final Pattern PART_INDICATOR = Pattern.compile(
            "^" + MultiPartFileDetector.getPartIndicatorRegEx() + "$", Pattern.CASE_INSENSITIVE);

    private static final Pattern PART_NUMBER = Pattern.compile("^\\d$");

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
