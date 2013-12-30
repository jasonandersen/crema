package crema.util.text;

import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Cleans up any whitespace within tokens, trims tokens and deletes any whitespace only or 
 * empty tokens.
 * 
 * @author Jason Andersen (andersen.jason@gmail.com)
 */
public class WhitespaceCleanerDecorator implements TokenDecorator {

    private static Logger log = LoggerFactory.getLogger(WhitespaceCleanerDecorator.class);

    /**
     * @see crema.util.text.TokenDecorator#decorate(java.util.List, java.lang.String)
     */
    public void decorate(final List<String> tokens) {
        log.debug("tokens: {}", tokens);
        List<Integer> tokenIndicesToRemove = new LinkedList<Integer>();
        for (int index = 0; index < tokens.size(); index++) {
            String cleanedToken = tokens.get(index).trim();
            if (cleanedToken.isEmpty()) {
                tokenIndicesToRemove.add(index);
            } else {
                tokens.set(index, cleanedToken);
            }
        }
        for (int index = tokenIndicesToRemove.size() - 1; index >= 0; index--) {
            Integer removalIndex = tokenIndicesToRemove.get(index);
            tokens.remove((int) removalIndex);
        }
    }

}
