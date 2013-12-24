package crema.util.tokenize;

import java.util.LinkedList;
import java.util.List;

/**
 * Cleans up any whitespace within tokens, trims tokens and deletes any whitespace only or 
 * empty tokens.
 * 
 * @author Jason Andersen (andersen.jason@gmail.com)
 */
public class WhitespaceCleanerDecorator implements TokenDecorator {

    /**
     * @see crema.util.tokenize.TokenDecorator#decorate(java.util.List, java.lang.String)
     */
    public void decorate(final List<String> tokens) {
        List<Integer> tokenIndicesToRemove = new LinkedList<Integer>();
        for (int index = 0; index < tokens.size(); index++) {
            String cleanedToken = tokens.get(index).trim();
            if (cleanedToken.isEmpty()) {
                tokenIndicesToRemove.add(index);
            } else {
                tokens.set(index, cleanedToken);
            }
        }
        for (Integer index : tokenIndicesToRemove) {
            tokens.remove((int) index);
        }
    }

}
