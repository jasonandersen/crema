package crema.util.tokenize;

import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Cleans out any common terms found in movie file names that aren't part of the title.
 * 
 * @author Jason Andersen (andersen.jason@gmail.com)
 */
public class CommonMovieCrapWordsDecorator implements TokenDecorator {

    private static final Pattern UNBOUNDED_YEAR_PATTERN =
            Pattern.compile("^(19|20)\\d\\d$", Pattern.CASE_INSENSITIVE);

    /**
     * @see crema.util.tokenize.TokenDecorator#decorate(java.util.List, java.lang.String)
     */
    public void decorate(final List<String> tokens) {
        List<String> tokensToRemove = new LinkedList<String>();
        int index = 0;
        for (String token : tokens) {
            if (shouldRemoveToken(token, index)) {
                tokensToRemove.add(token);
            }
            index++;
        }
        //clean out removed tokens
        for (String removeToken : tokensToRemove) {
            tokens.remove(removeToken);
        }
    }

    /**
     * Look for a match from within all the defined patterns.
     * @param token
     * @return true if a match was found
     */
    private boolean shouldRemoveToken(final String token, final int index) {
        if (isUnboundedYearAsFirstToken(token, index)) {
            return false;
        }
        if (CommonMovieCrapWords.isCrapWord(token)) {
            return true;
        }
        if (isUnboundedYear(token, index)) {
            return true;
        }
        return false;
    }

    /**
     * If an unbounded year (i.e. not surrounded by brackets, parens or braces) in the first token,
     * we don't want to replace it.
     * @param token
     * @param index
     * @return
     */
    private boolean isUnboundedYearAsFirstToken(final String token, final int index) {
        return index == 0 && UNBOUNDED_YEAR_PATTERN.matcher(token).matches();
    }

    /**
     * Looks for years without bounds (i.e. not surround by brackets, parens or braces) but only
     * if the token is not the first token (some movies are named a year - like 2012 or 2001 A Space Odyssey
     * so let's not delete that title).
     * @param token
     * @param index token's index within the list
     * @return true if this token is an unbounded year that should be removed
     */
    private boolean isUnboundedYear(final String token, final int index) {
        if (index == 0) {
            return false;
        }
        return UNBOUNDED_YEAR_PATTERN.matcher(token).matches();
    }
}
