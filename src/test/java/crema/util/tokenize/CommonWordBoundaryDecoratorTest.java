package crema.util.tokenize;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

/**
 * Tests the {@link TokenBoundaryDecorator} class.
 * 
 * @author Jason Andersen (andersen.jason@gmail.com)
 */
@RunWith(Parameterized.class)
public class CommonWordBoundaryDecoratorTest extends AbstractTokenDecoratorTest {

    @Parameters
    public static Collection<Object[]> buildTestParameters() {
        Collection<Object[]> p = new ArrayList<Object[]>();
        p.add(test(in("in.my.country"), out("in", "my", "country")));
        p.add(test(in("in-my-country"), out("in", "my", "country")));
        p.add(test(in("in_my_country"), out("in", "my", "country")));
        p.add(test(in("in my country"), out("in", "my", "country")));
        p.add(test(in("in|my|country"), out("in", "my", "country")));
        p.add(test(in("in.my|country"), out("in", "my", "country")));
        p.add(test(in("in_my-country"), out("in", "my", "country")));
        p.add(test(in("in+my+country"), out("in", "my", "country")));
        p.add(test(in("in my-country"), out("in", "my", "country")));

        return p;
    }

    /**
     * @param inputTokens
     * @param expectedTokens
     */
    public CommonWordBoundaryDecoratorTest(String[] inputTokens, String[] expectedTokens) {
        super(inputTokens, expectedTokens);
    }

    /**
     * @see crema.util.tokenize.AbstractTokenDecoratorTest#getDecorator()
     */
    @Override
    protected TokenDecorator getDecorator() {
        return new TokenBoundaryDecorator();
    }
}
