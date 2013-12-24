package crema.util.tokenize;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Component;

/**
 * Takes a movie file name and tokenizes it down into single words. Will also
 * clean up the tokens and remove tokens that aren't valuable.
 * 
 * @author Jason Andersen (andersen.jason@gmail.com)
 */
@Component
public class MovieNameTokenizer {

    private final List<TokenDecorator> decorators;

    /**
     * Constructor.
     */
    public MovieNameTokenizer() {
        decorators = new LinkedList<TokenDecorator>();
        decorators.add(new RemoveFileExtensionDecorator());
        decorators.add(new CamelCaseDecorator());
        decorators.add(new CommonWordBoundaryDecorator());
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
        return tokens;
    }

}
