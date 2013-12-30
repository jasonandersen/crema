package crema.util.text;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import crema.util.text.CommonMovieCrapWordsDecorator;
import crema.util.text.TokenDecorator;

/**
 * Testing the {@link CommonMovieCrapWorksDecorator} class.
 * 
 * @author Jason Andersen (andersen.jason@gmail.com)
 */
@RunWith(Parameterized.class)
public class CommonMovieCrapWordsDecoratorTest extends AbstractTokenDecoratorTest {

    @Parameters
    public static Collection<Object[]> buildTestParameters() {
        Collection<Object[]> p = new ArrayList<Object[]>();
        //remove resolutions
        p.add(test(in("Ladyhawke", "sd"), out("Ladyhawke")));
        p.add(test(in("Ladyhawke", "hd"), out("Ladyhawke")));
        p.add(test(in("Ladyhawke", "480i"), out("Ladyhawke")));
        p.add(test(in("Ladyhawke", "576i"), out("Ladyhawke")));
        p.add(test(in("Ladyhawke", "480p"), out("Ladyhawke")));
        p.add(test(in("Ladyhawke", "720p"), out("Ladyhawke")));
        p.add(test(in("Ladyhawke", "1080i"), out("Ladyhawke")));
        p.add(test(in("Ladyhawke", "1080p"), out("Ladyhawke")));
        p.add(test(in("Ladyhawke", "SD"), out("Ladyhawke")));
        p.add(test(in("Ladyhawke", "HD"), out("Ladyhawke")));
        p.add(test(in("Ladyhawke", "480I"), out("Ladyhawke")));
        p.add(test(in("Ladyhawke", "576I"), out("Ladyhawke")));
        p.add(test(in("Ladyhawke", "480P"), out("Ladyhawke")));
        p.add(test(in("Ladyhawke", "720P"), out("Ladyhawke")));
        p.add(test(in("Ladyhawke", "1080I"), out("Ladyhawke")));
        p.add(test(in("Ladyhawke", "1080P"), out("Ladyhawke")));
        //remove year numbers
        p.add(test(in("Ladyhawke", "(1984)"), out("Ladyhawke")));
        p.add(test(in("Ladyhawke", "[2010]"), out("Ladyhawke")));
        p.add(test(in("Ladyhawke", "1975"), out("Ladyhawke")));
        p.add(test(in("Ladyhawke", "1000"), out("Ladyhawke", "1000")));
        //random crap
        p.add(test(in("Ladyhawke", "x264"), out("Ladyhawke")));
        p.add(test(in("Ladyhawke", "X264"), out("Ladyhawke")));
        p.add(test(in("Ladyhawke", "[MISCCRAP]"), out("Ladyhawke")));
        p.add(test(in("Ladyhawke", "DVDRip"), out("Ladyhawke")));
        p.add(test(in("Ladyhawke", "DvDRip"), out("Ladyhawke")));
        p.add(test(in("Ladyhawke", "dvdrip"), out("Ladyhawke")));
        p.add(test(in("Ladyhawke", "BRRip"), out("Ladyhawke")));
        p.add(test(in("Ladyhawke", "BRayRip"), out("Ladyhawke")));
        p.add(test(in("Ladyhawke", "BrRiP"), out("Ladyhawke")));
        p.add(test(in("Ladyhawke", "1080p", "The", "Sequel"), out("Ladyhawke", "The", "Sequel")));
        p.add(test(in("Ladyhawke", "1986", "HD", "DvDRip", "720p", "X264"), out("Ladyhawke")));
        //if the movie's name is a year number, allow
        p.add(test(in("2012"), out("2012")));
        p.add(test(in("2001", "A", "Space", "Odyssey"), out("2001", "A", "Space", "Odyssey")));

        return p;
    }

    /**
     * @param inputTokens
     * @param expectedTokens
     */
    public CommonMovieCrapWordsDecoratorTest(String[] inputTokens, String[] expectedTokens) {
        super(inputTokens, expectedTokens);
    }

    /**
     * @see crema.util.text.AbstractTokenDecoratorTest#getDecorator()
     */
    @Override
    protected TokenDecorator getDecorator() {
        return new CommonMovieCrapWordsDecorator();
    }

}
