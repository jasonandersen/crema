package crema.util.text;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Uses common word separators to define token boundaries within a string.
 * 
 * @author Jason Andersen (andersen.jason@gmail.com)
 */
@Component
public class TokenBoundaryDecorator implements TokenDecorator {

    /**
     * Regular expression to define common word boundaries.
     */
    private static final String REGEX_COMMON_BOUNDARIES = "(\\(|\\)|\\[|\\]|\\s|\\.|-|_|\\+|\\|)";

    /**
     * Regular expression to split tokens on camel case notation.
     * For what it's worth, I didn't write this regex. I copied it from here:
     * http://stackoverflow.com/questions/7593969/regex-to-split-camelcase-or-titlecase-advanced
     */
    private static final String REGEX_CAMEL_CASE = "(?<!(^|[A-Z]))(?=[A-Z])|(?<!^)(?=[A-Z][a-z])";

    private CommonMovieCrapWordsMatcher crapWordsMatcher;

    /**
     * @param commonMovieCrapWordsMatcher
     */
    @Autowired
    public TokenBoundaryDecorator(final CommonMovieCrapWordsMatcher crapWordsMatcher) {
        this.crapWordsMatcher = crapWordsMatcher;
    }

    /**
     * @see crema.util.text.TokenDecorator#decorate(java.util.List, java.lang.String)
     */
    public void decorate(final List<String> tokens) {
        List<String> newTokens = splitOnWordBoundaries(tokens);
        newTokens = splitOnCamelCase(newTokens);
        tokens.clear();
        tokens.addAll(newTokens);
    }

    /**
     * Split on common word boundaries like spaces, dashes and underscores.
     * @param tokens
     * @return a new set of tokens split up
     */
    private List<String> splitOnWordBoundaries(final List<String> tokens) {
        List<String> newTokens = new LinkedList<String>();
        for (String token : tokens) {
            String[] splitResult = token.split(REGEX_COMMON_BOUNDARIES);
            newTokens.addAll(Arrays.asList(splitResult));
        }
        return newTokens;
    }

    /**
     * Split on camel case.
     * @param tokens
     * @return a new set of tokens split up
     */
    private List<String> splitOnCamelCase(final List<String> tokens) {
        List<String> newTokens = new LinkedList<String>();
        for (String token : tokens) {
            if (crapWordsMatcher.isCrapWord(token)) {
                /*
                 * If the token is a crap word, just add it as is and we'll let other decorators clean it up
                 */
                newTokens.add(token);
            } else {
                String[] subTokens = token.split(REGEX_CAMEL_CASE);
                newTokens.addAll(Arrays.asList(subTokens));
            }
        }
        return newTokens;
    }

}
