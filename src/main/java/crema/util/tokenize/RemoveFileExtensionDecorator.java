package crema.util.tokenize;

import java.util.List;

/**
 * Removes the file extension off of any token.
 * 
 * @author Jason Andersen (andersen.jason@gmail.com)
 */
public class RemoveFileExtensionDecorator implements TokenDecorator {

    private static final String EXT_MARKER = ".";

    /**
     * @see crema.util.tokenize.TokenDecorator#decorate(java.util.List)
     */
    public void decorate(final List<String> tokens) {
        for (int index = 0; index < tokens.size(); index++) {
            String token = tokens.get(index);
            tokens.set(index, cleanToken(token));
        }
    }

    /**
     * Cleans the file extension of end of the token.
     * @param token
     * @return
     */
    private String cleanToken(final String token) {
        int position = token.lastIndexOf(EXT_MARKER);
        if (position <= 0) {
            return token;
        }
        return token.substring(0, position);
    }

}
