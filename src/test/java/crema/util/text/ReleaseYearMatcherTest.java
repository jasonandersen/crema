package crema.util.text;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

/**
 * Testing the {@link ReleaseYearMatcher} class.
 * 
 * @author Jason Andersen (andersen.jason@gmail.com)
 */
@RunWith(Parameterized.class)
public class ReleaseYearMatcherTest {

    @Parameters(name = "{0}={1}")
    public static Collection<Object[]> buildTestParameters() {
        return Arrays.asList(new Object[][] {
                { "MONKEY2000", null },
                { "1900", "1900" },
                { "2013", "2013" },
                { "1899", null },
                { "2029", "2029" },
                { "2030", null },
                { "1000", null },
                { "3000", null },
                { "1", null },
                { "(1900)", "1900" },
                { "(2013)", "2013" },
                { "(1899)", null },
                { "(2029)", "2029" },
                { "(2030)", null },
                { "(1000)", null },
                { "(3000)", null },
                { "(1)", null },
                { "[1900]", "1900" },
                { "[2013]", "2013" },
                { "[1899]", null },
                { "[2029]", "2029" },
                { "[2030]", null },
                { "[1000]", null },
                { "[3000]", null },
                { "[1]", null },
                { "{1900}", "1900" },
                { "{2013}", "2013" },
                { "{1899}", null },
                { "{2029}", "2029" },
                { "{2030}", null },
                { "{1000}", null },
                { "{3000}", null },
                { "{1}", null },
        });
    }

    private String token;

    private String expectedYear;

    private ReleaseYearMatcher matcher;

    /**
     * Constructor.
     * @param token
     */
    public ReleaseYearMatcherTest(String token, String expectedYear) {
        this.token = token;
        this.expectedYear = expectedYear;
        matcher = new ReleaseYearMatcher();
    }

    @Test
    public void test() {
        assertEquals(expectedYear, matcher.matches(token));
    }
}
