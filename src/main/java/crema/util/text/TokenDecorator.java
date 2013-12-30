package crema.util.text;

import java.util.List;

/**
 * Decorates a list of tokens.
 * 
 * @author Jason Andersen (andersen.jason@gmail.com)
 */
public interface TokenDecorator {

    /**
     * Decorates the list of tokens.
     * @param tokens
     */
    void decorate(List<String> tokens);
}
