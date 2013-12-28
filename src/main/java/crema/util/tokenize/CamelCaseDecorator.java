package crema.util.tokenize;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Splits tokens based on camel case.
 * 
 * @author Jason Andersen (andersen.jason@gmail.com)
 */
public class CamelCaseDecorator implements TokenDecorator {

    /**
     * @see crema.util.tokenize.TokenDecorator#decorate(java.util.List)
     */
    public void decorate(final List<String> tokens) {
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
        tokens.clear();
        tokens.addAll(newTokens);
    }

}
