package crema.util.tokenize;

import java.util.List;

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
public class TorrentFilePatternDecorator implements TokenDecorator {

    /**
     * @see crema.util.tokenize.TokenDecorator#decorate(java.util.List, java.lang.String)
     */
    public void decorate(final List<String> tokens) {
        throw new UnsupportedOperationException();
    }

}
