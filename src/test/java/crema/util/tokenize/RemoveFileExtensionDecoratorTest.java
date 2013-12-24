package crema.util.tokenize;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.runners.Parameterized.Parameters;

/**
 * Testing the {@link RemoveFileExtensionDecorator} class.
 * 
 * @author Jason Andersen (andersen.jason@gmail.com)
 */
public class RemoveFileExtensionDecoratorTest extends AbstractTokenDecoratorTest {

    @Parameters
    public static Collection<Object[]> buildTestParameters() {
        Collection<Object[]> p = new ArrayList<Object[]>();
        p.add(test(in("here is my file.avi"), out("here is my file")));
        p.add(test(in("here is my file.AVI"), out("here is my file")));
        p.add(test(in("here.is.my.file.avi"), out("here.is.my.file")));
        p.add(test(in("here.is.my.file.longextension"), out("here.is.my.file")));
        p.add(test(in(".blahblah"), out(".blahblah")));

        return p;
    }

    /**
     * Constructor.
     * @param inputTokens
     * @param expectedTokens
     */
    public RemoveFileExtensionDecoratorTest(String[] inputTokens, String[] expectedTokens) {
        super(inputTokens, expectedTokens);
    }

    /**
     * @see crema.util.tokenize.AbstractTokenDecoratorTest#getDecorator()
     */
    @Override
    protected TokenDecorator getDecorator() {
        return new RemoveFileExtensionDecorator();
    }
}
