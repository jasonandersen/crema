package crema.util.tokenize;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Base class to extend in order to test individual {@link TokenDecorator} classes.
 * 
 * @author Jason Andersen (andersen.jason@gmail.com)
 */
@RunWith(Parameterized.class)
public abstract class AbstractTokenDecoratorTest {

    /*
    @Parameters
    public static Collection<Object[]> buildTestParameters() {
        Collection<Object[]> p = new ArrayList<Object[]>();
        p.add(test(in(""), out("")));
        p.add(test(in(""), out("")));
        p.add(test(in(""), out("")));
        p.add(test(in(""), out("")));
        p.add(test(in(""), out("")));
        p.add(test(in(""), out("")));
        p.add(test(in(""), out("")));
        p.add(test(in(""), out("")));
        p.add(test(in(""), out("")));
        p.add(test(in(""), out("")));
        p.add(test(in(""), out("")));
        p.add(test(in(""), out("")));
        p.add(test(in(""), out("")));
        p.add(test(in(""), out("")));
        p.add(test(in(""), out("")));
        p.add(test(in(""), out("")));
        p.add(test(in(""), out("")));
        p.add(test(in(""), out("")));
        p.add(test(in(""), out("")));
        p.add(test(in(""), out("")));
        p.add(test(in(""), out("")));
        p.add(test(in(""), out("")));
        p.add(test(in(""), out("")));
        p.add(test(in(""), out("")));
        p.add(test(in(""), out("")));
        p.add(test(in(""), out("")));
        p.add(test(in(""), out("")));
        p.add(test(in(""), out("")));
        p.add(test(in(""), out("")));

        return p;
    }
     */

    private static Logger log = LoggerFactory.getLogger(AbstractTokenDecoratorTest.class);

    /**
     * Convenience method to make parameter building easier and more readable
     * @param params
     * @return
     */
    protected static Object[] test(Object... params) {
        return params;
    }

    /**
     * Convenience method to make parameter building easier and more readable
     * @param params
     * @return
     */
    protected static String[] in(String... params) {
        return params;
    }

    /**
     * Convenience method to make parameter building easier and more readable
     * @param params
     * @return
     */
    protected static String[] out(String... params) {
        return params;
    }

    /**
     * Token decorator to test.
     */
    protected TokenDecorator decorator;
    /**
     * The list of tokens prior to running the decorator.
     */
    protected List<String> inputTokens;
    /**
     * The list of tokens after running the decorator.
     */
    protected List<String> expectedTokens;

    /**
     * Constructor to setup an individual test case.
     * @param testCase
     */
    public AbstractTokenDecoratorTest(String[] inputTokens, String[] expectedTokens) {
        this.inputTokens = new LinkedList<String>(Arrays.asList(inputTokens));
        this.expectedTokens = Arrays.asList(expectedTokens);
        decorator = getDecorator();
    }

    @Test
    public void testTokenDecorator() {
        log.debug("preTokens={};postTokens={};", inputTokens, expectedTokens);
        decorator.decorate(inputTokens);
        assertEquals(expectedTokens, inputTokens);
    }

    /**
     * Instantiate decorator to test.
     * @return
     */
    protected abstract TokenDecorator getDecorator();

}
