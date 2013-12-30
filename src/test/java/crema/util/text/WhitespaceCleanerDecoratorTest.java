package crema.util.text;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import crema.util.text.TokenDecorator;
import crema.util.text.WhitespaceCleanerDecorator;

/**
 * Tests the {@link WhitespaceCleanerDecorator.
 * 
 * @author Jason Andersen (andersen.jason@gmail.com)
 */
@RunWith(Parameterized.class)
public class WhitespaceCleanerDecoratorTest extends AbstractTokenDecoratorTest {

    @Parameters
    public static Collection<Object[]> buildTestParameters() {
        Collection<Object[]> p = new ArrayList<Object[]>();
        //remove resolutions
        p.add(test(in(" Ladyhawke", " "), out("Ladyhawke")));
        p.add(test(in("Ladyhawke  ", "   "), out("Ladyhawke")));
        p.add(test(in("      Ladyhawke   ", ""), out("Ladyhawke")));
        p.add(test(in(" ", "Ladyhawke"), out("Ladyhawke")));
        p.add(test(in("", "Ladyhawke"), out("Ladyhawke")));
        p.add(test(in("  ", "Ladyhawke"), out("Ladyhawke")));
        p.add(test(in(" Dances ", "With      ", "    Wolves"), out("Dances", "With", "Wolves")));

        return p;
    }

    /**
     * @param inputTokens
     * @param expectedTokens
     */
    public WhitespaceCleanerDecoratorTest(String[] inputTokens, String[] expectedTokens) {
        super(inputTokens, expectedTokens);
    }

    /**
     * @see crema.util.text.AbstractTokenDecoratorTest#getDecorator()
     */
    @Override
    protected TokenDecorator getDecorator() {
        return new WhitespaceCleanerDecorator();
    }

}
