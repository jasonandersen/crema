package crema.util.tokenize;

import org.junit.Test;

/**
 * Testing specific defects in the {@link MovieNameTokenizer} class.
 * 
 * @author Jason Andersen (andersen.jason@gmail.com)
 */
public class MovieNameTokenizerTest {

    private MovieNameTokenizer tokenizer = new MovieNameTokenizer();

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
}
