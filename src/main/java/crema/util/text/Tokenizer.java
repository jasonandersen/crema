package crema.util.text;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Splits strings into tokens and processes those tokens based on a list of
 * {@link TokenDecorator} objects.
 * 
 * @author Jason Andersen (andersen.jason@gmail.com)
 */
public class Tokenizer {

    private static Logger log = LoggerFactory.getLogger(Tokenizer.class);

    private final List<TokenDecorator> decorators;

    /**
     * Constructor.
     * @param decorators
     */
    public Tokenizer(final List<TokenDecorator> decorators) {
        Validate.notNull(decorators, "decorators cannot be null");
        this.decorators = decorators;
    }

    /**
     * @param fileName
     * @return
     */
    public List<String> tokenize(final String fileName) {
        List<String> tokens = new ArrayList<String>();
        tokens.add(fileName);
        for (TokenDecorator decorator : decorators) {
            decorator.decorate(tokens);
        }
        log.debug("file name: {} tokens: {}", fileName, tokens);
        return tokens;
    }

}
