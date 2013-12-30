package crema.util.text;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.runners.Parameterized.Parameters;

/**
 * Testing the {@link CaseCorrectionDecorator} class.
 * 
 * @author Jason Andersen (andersen.jason@gmail.com)
 */
public class CaseCorrectionDecoratorTest extends AbstractTokenDecoratorTest {

    @Parameters
    public static Collection<Object[]> buildTestParameters() {
        Collection<Object[]> p = new ArrayList<Object[]>();
        p.add(test(in("I", "LIKE", "MONKEYS"), out("I", "Like", "Monkeys")));
        p.add(test(in("i", "like", "monkeys"), out("I", "Like", "Monkeys")));
        p.add(test(in("i", "lIKE", "mOnKEYS"), out("I", "Like", "Monkeys")));
        p.add(test(in("i", "lIkE", "MoNkEyS"), out("I", "Like", "Monkeys")));
        p.add(test(in("I", "Like", "Monkeys", "part", "III"), out("I", "Like", "Monkeys", "Part", "III")));
        p.add(test(in("I", "Like", "Monkeys", "part", "iii"), out("I", "Like", "Monkeys", "Part", "III")));
        p.add(test(in("I", "Like", "Monkeys", "part", "i"), out("I", "Like", "Monkeys", "Part", "I")));
        p.add(test(in("I", "Like", "Monkeys", "part", "iv"), out("I", "Like", "Monkeys", "Part", "IV")));
        p.add(test(in("I", "Like", "Monkeys", "part", "xii"), out("I", "Like", "Monkeys", "Part", "XII")));
        p.add(test(in("I", "Like", "Monkeys", "part", "xiv"), out("I", "Like", "Monkeys", "Part", "XIV")));
        return p;
    }

    /**
     * Constructor.
     * @param inputTokens
     * @param expectedTokens
     */
    public CaseCorrectionDecoratorTest(String[] inputTokens, String[] expectedTokens) {
        super(inputTokens, expectedTokens);
    }

    /**
     * @see crema.util.text.AbstractTokenDecoratorTest#getDecorator()
     */
    @Override
    protected TokenDecorator getDecorator() {
        return new CaseCorrectionDecorator();
    }

}
