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

    /*
     * common word tokens (. _ - " ")
     * strip empty tokens
     * remove year (1993) or [1993]
     * remove 2008.LIMITED.DVDRip.XviD-DMT kind of crap pattern
     * remove resolution (HD, 480i, 576i, 480p, 720p, 1080i, 1080p)
     * trim all the tokens
     */

    private final List<TokenDecorator> decorators;

    /**
     * Constructor.
     */
    public MovieNameTokenizer() {
        decorators = new LinkedList<TokenDecorator>();
        decorators.add(new RemoveFileExtensionDecorator());
        decorators.add(new TorrentFilePatternDecorator());
        decorators.add(new CommonWordSeparatorDecorator());
        decorators.add(new WhitespaceCleanerDecorator());
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
