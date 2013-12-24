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

    /*
     * Some examples:
     * 
     * Somers.Town.2008.LIMITED.DVDRip.XviD-DMT
     * Half.Nelson.LiMiTED.DVDRip.XviD-LMG
     * I'm.Not.There.[2007].DvDrip[Eng]-aXXo
     * Last Chance Harvey[2008]DvDrip[Eng]-FXG
     * The.Dark.Knight.Returns.2013.iNTERNAL.BDRi
     * Snatch.2000.1080p.AC3(Dolby).5.1ch.Blu-ray.PS3-TEAM
     * Snatch (2000) - IPOD CLASSIC by empetrojki
     * Mystery.Men.1999.BDRip.576P.X264.AC3-FaNGDiNG0
     * Mystery Men (1999) [HDDVDMux720p.Ita-Eng-Spa][Nautilus-BT]
     * Snatch (2000) 720p BrRip x264 - DagarX
     */

    /**
     * @see crema.util.tokenize.TokenDecorator#decorate(java.util.List, java.lang.String)
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
            if (CommonMovieCrapWords.isCrapWord(token)) {
                return index;
            }
        }
        return -1;
    }

}
