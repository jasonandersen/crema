package crema.util.text;

import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

    private final Tokenizer tokenizer;

    /**
     * Constructor.
     */
    @Autowired
    public MovieNameTokenizer(
            final TokenBoundaryDecorator tokenBoundaryDecorator,
            final WhitespaceCleanerDecorator whitespaceCleanerDecorator,
            final TorrentFilePatternDecorator torrentFilePatternDecorator,
            final CommonMovieCrapWordsDecorator commonMovieCrapWordsDecorator,
            final MultiPartIndicatorDecorator multiPartIndicatorDecorator,
            final CaseCorrectionDecorator caseCorrectionDecorator) {

        List<TokenDecorator> decorators = new LinkedList<TokenDecorator>();
        decorators = new LinkedList<TokenDecorator>();
        /*
         * split the file name up into word tokens
         */
        decorators.add(tokenBoundaryDecorator);
        /*
         * clean up the whitespace in the tokens
         */
        decorators.add(whitespaceCleanerDecorator);
        /*
         * clean out the tokens that aren't part of the movie name
         */
        decorators.add(torrentFilePatternDecorator);
        decorators.add(commonMovieCrapWordsDecorator);
        decorators.add(multiPartIndicatorDecorator);
        /*
         * correct the case of the tokens
         */
        decorators.add(caseCorrectionDecorator);

        tokenizer = new Tokenizer(decorators);
    }

    /**
     * @param fileName
     * @return
     */
    public List<String> tokenize(final String fileName) {
        log.debug("tokenizing {}", fileName);
        return tokenizer.tokenize(fileName);
    }

}
