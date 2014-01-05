package crema.util.text;

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
public class TokenBoundaryDecoratorTest extends AbstractTokenDecoratorTest {

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

        //camel case
        p.add(test(in("CamelCaseIsFun"), out("Camel", "Case", "Is", "Fun")));
        p.add(test(in("camelCaseIsFun"), out("camel", "Case", "Is", "Fun")));
        p.add(test(in("MONKEY"), out("MONKEY")));

        //make sure crap words don't get split on camel case
        p.add(test(in("DvDrip"), out("DvDrip")));

        return p;
    }

    /**
     * @param inputTokens
     * @param expectedTokens
     */
    public TokenBoundaryDecoratorTest(String[] inputTokens, String[] expectedTokens) {
        super(inputTokens, expectedTokens);
    }

    /**
     * @see crema.util.text.AbstractTokenDecoratorTest#getDecorator()
     */
    @Override
    protected TokenDecorator getDecorator() {
        return new TokenBoundaryDecorator(new CommonMovieCrapWordsMatcher());
    }
}
