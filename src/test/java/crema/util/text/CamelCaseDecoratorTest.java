package crema.util.text;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.runners.Parameterized.Parameters;

/**
 * Testing the {@link CamelCaseDecorator} class.
 * 
 * @author Jason Andersen (andersen.jason@gmail.com)
 */
public class CamelCaseDecoratorTest extends AbstractTokenDecoratorTest {

    @Parameters
    public static Collection<Object[]> buildTestParameters() {
        Collection<Object[]> p = new ArrayList<Object[]>();
        p.add(test(in("CamelCaseIsFun"), out("Camel", "Case", "Is", "Fun")));
        p.add(test(in("camelCaseIsFun"), out("camel", "Case", "Is", "Fun")));
        p.add(test(in("MONKEY"), out("MONKEY")));
        //make sure crap words don't get split on camel case
        p.add(test(in("DvDrip"), out("DvDrip")));
        return p;
    }

    /**
     * Constructor.
     * @param inputTokens
     * @param expectedTokens
     */
    public CamelCaseDecoratorTest(String[] inputTokens, String[] expectedTokens) {
        super(inputTokens, expectedTokens);
    }

    /**
     * @see crema.util.text.AbstractTokenDecoratorTest#getDecorator()
     */
    @Override
    protected TokenDecorator getDecorator() {
        return new CamelCaseDecorator();
    }

}
