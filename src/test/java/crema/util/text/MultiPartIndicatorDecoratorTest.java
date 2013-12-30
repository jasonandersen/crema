package crema.util.text;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.runners.Parameterized.Parameters;

/**
 * Test the {@link MultiPartIndicatorDecorator} class.
 * 
 * @author Jason Andersen (andersen.jason@gmail.com)
 */
public class MultiPartIndicatorDecoratorTest extends AbstractTokenDecoratorTest {

    @Parameters
    public static Collection<Object[]> buildTestParameters() {
        Collection<Object[]> p = new ArrayList<Object[]>();
        p.add(test(in("Snatch", "Part", "1"), out("Snatch")));
        p.add(test(in("Snatch", "Part", "2"), out("Snatch")));
        p.add(test(in("Snatch", "Part", "9"), out("Snatch")));
        p.add(test(in("Snatch", "part", "1"), out("Snatch")));
        p.add(test(in("Snatch", "PART", "1"), out("Snatch")));
        p.add(test(in("Snatch", "cd", "1"), out("Snatch")));
        p.add(test(in("Snatch", "CD", "1"), out("Snatch")));
        p.add(test(in("Snatch", "Cd", "1"), out("Snatch")));
        p.add(test(in("Snatch", "Disc", "1"), out("Snatch")));
        p.add(test(in("Snatch", "disc", "1"), out("Snatch")));
        p.add(test(in("Snatch", "DISC", "1"), out("Snatch")));

        p.add(test(in("Snatch", "part1"), out("Snatch")));
        p.add(test(in("Snatch", "cd01"), out("Snatch")));

        return p;
    }

    /**
     * Constructor.
     * @param inputTokens
     * @param expectedTokens
     */
    public MultiPartIndicatorDecoratorTest(String[] inputTokens, String[] expectedTokens) {
        super(inputTokens, expectedTokens);
    }

    /**
     * @see crema.util.text.AbstractTokenDecoratorTest#getDecorator()
     */
    @Override
    protected TokenDecorator getDecorator() {
        return new MultiPartIndicatorDecorator();
    }

}
