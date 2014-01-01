package crema.util.text;

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
     * @return
     */
    private List<String> splitOnWordBoundaries(final List<String> tokens) {
        List<String> newTokens = new LinkedList<String>();
        for (String token : tokens) {
            String[] splitResult = token.split("(\\(|\\)|\\[|\\]|\\s|\\.|-|_|\\+|\\|)");
            newTokens.addAll(Arrays.asList(splitResult));
        }
        return newTokens;
    }

    /**
     * Split on camel base.
     * @param tokens
     * @return
     */
    private List<String> splitOnCamelCase(final List<String> tokens) {
        List<String> newTokens = new LinkedList<String>();
        for (String token : tokens) {
            if (CommonMovieCrapWords.isCrapWord(token)) {
                /*
                 * If the token is a crap word, just add it as is and we'll let other decorators clean it up
                 */
                newTokens.add(token);
            } else {
                /*
                 * For what it's worth, I didn't write this regex. I copied it from here:
                 * http://stackoverflow.com/questions/7593969/regex-to-split-camelcase-or-titlecase-advanced
                 */
                String[] subTokens = token.split("(?<!(^|[A-Z]))(?=[A-Z])|(?<!^)(?=[A-Z][a-z])");
                newTokens.addAll(Arrays.asList(subTokens));
            }
        }
        return newTokens;
    }

}
