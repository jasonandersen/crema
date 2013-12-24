package crema.util.tokenize;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Uses common word separators define word boundaries within a string.
 * 
 * @author Jason Andersen (andersen.jason@gmail.com)
 */
public class CommonWordBoundaryDecorator implements TokenDecorator {

    private static Logger log = LoggerFactory.getLogger(CommonWordBoundaryDecorator.class);

    /**
     * @see crema.util.tokenize.TokenDecorator#decorate(java.util.List, java.lang.String)
     */
    public void decorate(final List<String> tokens) {
        List<String> newTokens = new LinkedList<String>();
        for (String token : tokens) {
            String[] splitResult = token.split("(\\(|\\)|\\[|\\]|\\s|\\.|-|_|\\+|\\|)");
            newTokens.addAll(Arrays.asList(splitResult));
        }
        log.debug("original tokens {}", tokens);
        log.debug("new tokens {}", newTokens);
        tokens.clear();
        tokens.addAll(newTokens);
    }

}
