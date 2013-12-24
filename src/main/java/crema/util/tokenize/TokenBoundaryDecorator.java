package crema.util.tokenize;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Uses common word separators to define token boundaries within a string.
 * 
 * @author Jason Andersen (andersen.jason@gmail.com)
 */
public class TokenBoundaryDecorator implements TokenDecorator {

    /**
     * @see crema.util.tokenize.TokenDecorator#decorate(java.util.List, java.lang.String)
     */
    public void decorate(final List<String> tokens) {
        List<String> newTokens = new LinkedList<String>();
        for (String token : tokens) {
            String[] splitResult = token.split("(\\(|\\)|\\[|\\]|\\s|\\.|-|_|\\+|\\|)");
            newTokens.addAll(Arrays.asList(splitResult));
        }
        tokens.clear();
        tokens.addAll(newTokens);
    }

}
