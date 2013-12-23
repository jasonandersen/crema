package crema.util.tokenize;

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
        throw new UnsupportedOperationException();
    }

}
