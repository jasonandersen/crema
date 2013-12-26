package crema.util.tokenize;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Takes a movie file name and tokenizes it down into single words. Will also
 * clean up the tokens and remove tokens that aren't valuable.
 * 
 * @author Jason Andersen (andersen.jason@gmail.com)
 */
@Service
public class MovieNameTokenizer {

    private static Logger log = LoggerFactory.getLogger(MovieNameTokenizer.class);

    private final List<TokenDecorator> decorators;

    /**
     * Constructor.
     */
    public MovieNameTokenizer() {
        decorators = new LinkedList<TokenDecorator>();
        decorators.add(new CamelCaseDecorator());
        decorators.add(new TokenBoundaryDecorator());
        decorators.add(new WhitespaceCleanerDecorator());
        decorators.add(new TorrentFilePatternDecorator());
        decorators.add(new CommonMovieCrapWordsDecorator());
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
