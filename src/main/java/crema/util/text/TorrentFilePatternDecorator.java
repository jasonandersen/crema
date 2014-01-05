package crema.util.text;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Tries to take movie names typically found from torrent files and reduce them down
 * to something legible. For example: <p/>
 * 
 * Mystery.Men.1999.BDRip.576P.X264.AC3-FaNGDiNG0
 * <p />
 * 
 * Should be reduced to:
 * <p />
 * 
 * Mystery.Men
 * 
 * @author Jason Andersen (andersen.jason@gmail.com)
 */
@Component
public class TorrentFilePatternDecorator implements TokenDecorator {

    private final CommonMovieCrapWordsMatcher crapWordsMatcher;

    /**
     * Constructor.
     * @param crapWordsMatcher
     */
    @Autowired
    public TorrentFilePatternDecorator(final CommonMovieCrapWordsMatcher crapWordsMatcher) {
        this.crapWordsMatcher = crapWordsMatcher;
    }

    /**
     * @see crema.util.text.TokenDecorator#decorate(java.util.List, java.lang.String)
     */
    public void decorate(final List<String> tokens) {
        int firstCrapTokenIndex = findFirstCrapToken(tokens);
        if (firstCrapTokenIndex < 0) {
            //no crap tokens are found
            return;
        }
        if (firstCrapTokenIndex == 0) {
            //if the crap token is the first token, don't do anything
            return;
        }
        //clean out the crap token and every thing after it
        for (int index = tokens.size() - 1; index >= firstCrapTokenIndex; index--) {
            tokens.remove(index);
        }
    }

    /**
     * Finds the first token that matches a list of crap words.
     * @param tokens
     * @return the index within the list of the first crap token, will return
     *      -1 if no crap token is founded
     */
    private int findFirstCrapToken(final List<String> tokens) {
        int index;
        for (index = 0; index < tokens.size(); index++) {
            String token = tokens.get(index);
            if (crapWordsMatcher.isCrapWord(token)) {
                return index;
            }
        }
        return -1;
    }

}
