package crema.util.text;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.runners.Parameterized.Parameters;

/**
 * Testing the {@link TorrentFilePatternDecorator} class.
 * 
 * @author Jason Andersen (andersen.jason@gmail.com)
 */
public class TorrentFilePatternDecoratorTest extends AbstractTokenDecoratorTest {

    @Parameters
    public static Collection<Object[]> buildTestParameters() {
        Collection<Object[]> p = new ArrayList<Object[]>();
        p.add(test(in("Somers", "Town", "2008", "LIMITED", "DVDRip", "XviD", "DMT"), out("Somers", "Town")));
        p.add(test(in("Half", "Nelson", "1080p", "DVDRip", "XviD", "LMG"), out("Half", "Nelson")));
        p.add(test(in("I'm", "Not", "There", "[2007]", "DvDrip[Eng]", "aXXo"), out("I'm", "Not", "There")));
        p.add(test(in("Last", "Chance", "Harvey", "[2008]", "DvDrip", "[Eng]", "FXG"), out("Last", "Chance", "Harvey")));
        p.add(test(in("The", "Dark", "Knight", "Returns", "2013", "iNTERNAL", "BDRi"),
                out("The", "Dark", "Knight", "Returns")));
        p.add(test(in("Snatch", "2000", "1080p", "AC3(Dolby)", "5", "1ch", "Blu-ray", "PS3-TEAM"), out("Snatch")));
        p.add(test(in("Snatch", "2000", "IPOD", "CLASSIC", "by", "empetrojki"), out("Snatch")));
        p.add(test(in("Mystery", "Men", "1999", "BDRip", "576P", "X264", "AC3", "FaNGDiNG0"), out("Mystery", "Men")));
        p.add(test(in("Mystery", "Men", "(1999)", "[HDDVDMux720p", "Ita", "Eng", "Spa]", "[Nautilus-BT]"),
                out("Mystery", "Men")));
        p.add(test(in("Snatch", "(2000)", "720p", "BrRip", "x264", "DagarX"), out("Snatch")));

        return p;
    }

    /**
     * Constructor.
     * @param inputTokens
     * @param expectedTokens
     */
    public TorrentFilePatternDecoratorTest(String[] inputTokens, String[] expectedTokens) {
        super(inputTokens, expectedTokens);
    }

    /**
     * @see crema.util.text.AbstractTokenDecoratorTest#getDecorator()
     */
    @Override
    protected TokenDecorator getDecorator() {
        return new TorrentFilePatternDecorator(new CommonMovieCrapWordsMatcher());
    }

}
