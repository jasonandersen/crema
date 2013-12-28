package crema.util.tokenize;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

/**
 * Testing specific defects in the {@link MovieNameTokenizer} class.
 * 
 * @author Jason Andersen (andersen.jason@gmail.com)
 */
public class MovieNameTokenizerTest {

    private MovieNameTokenizer tokenizer = new MovieNameTokenizer();

    private List<String> tokens;

    /**
     * Getting an exception out of the WhitespaceCleanerTokenDecorator:
     * java.lang.IndexOutOfBoundsException: Index: 9, Size: 9
     * 
     * When passing in the following title.
     */
    @Test
    public void testDefectInSimpsonsTitle() {
        tokenizer.tokenize("The Simpsons - s16e01 - Treehouse Of Horror XV .lol");
    }

    /**
     * Not handling this title correctly due to camel case in a crap word:
     * The.Indian.In.The.Cupboard.DvDrip[Eng]-aXXo.avi
     */
    @Test
    public void testCamelCaseCrapWordDefect() {
        tokens = tokenizer.tokenize("The.Indian.In.The.Cupboard.DvDrip[Eng]-aXXo");
        assertExpectedTokens("The", "Indian", "In", "The", "Cupboard");
    }

    /**
     * Assert that the we got the tokens we expected to get.
     * @param expectedTokens
     */
    private void assertExpectedTokens(String... expectedTokens) {
        List<String> expectedTokensList = Arrays.asList(expectedTokens);
        assertEquals(expectedTokensList, tokens);
    }
}
