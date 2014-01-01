package crema.util.text;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

/**
 * Testing the {@link FileSize} class.
 * 
 * @author Jason Andersen (andersen.jason@gmail.com)
 */
@RunWith(Parameterized.class)
public class FileSizeTest {

    @Parameters(name = "{0} = {1}")
    public static Collection<Object[]> buildTestParameters() {
        return Arrays.asList(new Object[][] {
                { 500000L, "488KB" },
                { 1000000L, "1MB" },
                { 500000000L, "488MB" },
                { 1000000000L, "1.0GB" },
                { 1100000000L, "1.1GB" },
                { 9500000000L, "9.3GB" }
        });
    }

    private long size;

    private String sizeDisplay;

    private FileSize fileSize;

    public FileSizeTest(final long size, final String sizeDisplay) {
        this.size = size;
        this.sizeDisplay = sizeDisplay;
    }

    @Test
    public void test() {
        fileSize = new FileSize(size);
        assertEquals(sizeDisplay, fileSize.toString());
    }

}
