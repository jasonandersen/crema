package crema.util.text;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 
 * @author Jason Andersen (andersen.jason@gmail.com)
 */
@RunWith(Parameterized.class)
public class CommonMovieCrapWordsTest {

    @Parameters(name = "{0}={1}")
    public static Collection<Object[]> buildTestParameters() {
        return Arrays.asList(new Object[][] {
                { "2008", true },
                { "{2008}", true },
                { "[2008]", true },
                { "(2008)", true },
                { "dvdrip", true },
                { "bluray", true },
                { "blueray", true },
                { "BR", true },
                { "AC3", true },
                { "AAC", true },
                { "DVDRip", true },
                { "DvDrIp", true },
                { "DVDRIP", true },
                { "DVDSCR", true },
                { "BRRip", true },
                { "bdrip", true },
                { "60fps", true },
                { "brayrip", true },
                { "xvid", true },
                { "monkey", false }
        });
    }

    private String word;

    private boolean expectedResult;

    @Autowired
    private CommonMovieCrapWordsMatcher crapWordsMatcher;

    /**
     * Constructor.
     * @param word
     * @param expectedResult
     */
    public CommonMovieCrapWordsTest(String word, boolean expectedResult) {
        this.word = word;
        this.expectedResult = expectedResult;
        this.crapWordsMatcher = new CommonMovieCrapWordsMatcher();
    }

    @Test
    public void testIsCrapWord() {
        boolean result = crapWordsMatcher.isCrapWord(word);
        assertEquals(expectedResult, result);
    }
}
