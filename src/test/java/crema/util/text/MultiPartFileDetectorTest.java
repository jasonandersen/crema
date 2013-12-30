package crema.util.text;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

/**
 * Testing the {@link MultiPartFileDetector} class.
 * 
 * @author Jason Andersen (andersen.jason@gmail.com)
 */
@RunWith(Parameterized.class)
public class MultiPartFileDetectorTest {

    @Parameters(name = "{0}, {1} = {2}")
    public static Collection<Object[]> buildTestParameters() {
        return Arrays.asList(new Object[][] {
                //part
                { "src/test/file-part1.avi", "src/test/file-part2.avi", true },
                { "src/test/file-part1(1997).avi", "src/test/file-part2(1997).avi", true },
                { "src/test/file.avi", "src/test/some-other-file.avi", false },
                { "src/test/file-part1.avi", "src/test/totally-different-file-part2.avi", false },
                { "src/test/file part 1.avi", "src/test/file part 2.avi", true },
                { "src/test/FILE PART 1.avi", "src/test/FILE PART 2.avi", true },
                { "src/test/file-part1.avi", "src/test/file-a-different-file.avi", false },
                { "src/test/file-part-1.avi", "src/test/file-part-2.avi", true },

                //CD
                { "src/test/file-cd1.avi", "src/test/file-cd2.avi", true },
                { "src/test/file-cd1(1997).avi", "src/test/file-cd2(1997).avi", true },
                { "src/test/file.avi", "src/test/some-other-file.avi", false },
                { "src/test/file-cd1.avi", "src/test/totally-different-file-cd2.avi", false },
                { "src/test/file cd 1.avi", "src/test/file cd 2.avi", true },
                { "src/test/FILE CD 1.avi", "src/test/FILE CD 2.avi", true },
                { "src/test/file-cd1.avi", "src/test/file-a-different-file.avi", false },
                { "src/test/file-cd-1.avi", "src/test/file-cd-2.avi", true },

                //disc
                { "src/test/file-disc1.avi", "src/test/file-disc2.avi", true },
                { "src/test/file-disc1(1997).avi", "src/test/file-disc2(1997).avi", true },
                { "src/test/file.avi", "src/test/some-other-file.avi", false },
                { "src/test/file-disc1.avi", "src/test/totally-different-file-disc2.avi", false },
                { "src/test/file disc 1.avi", "src/test/file disc 2.avi", true },
                { "src/test/FILE DISC 1.avi", "src/test/FILE DISC 2.avi", true },
                { "src/test/file-disc1.avi", "src/test/file-a-different-file.avi", false },
                { "src/test/file-disc-1.avi", "src/test/file-disc-2.avi", true },

                //multiple files
                { "src/test/file-part1.avi", "src/test/file-part3.avi", true },
                { "src/test/file-part1.avi", "src/test/file-part5.avi", true },
                { "src/test/file-part1.avi", "src/test/file-part7.avi", true },
                { "src/test/file-part1.avi", "src/test/file-part9.avi", true },

        });
    }

    private MultiPartFileDetector detector;

    private String originalFileName;

    private String siblingFileName;

    private boolean expectedResult;

    /**
     * Constructor
     * @param originalFileName
     * @param siblingFileName
     * @param expectedResult
     */
    public MultiPartFileDetectorTest(String originalFileName, String siblingFileName, boolean expectedResult) {
        this.originalFileName = originalFileName;
        this.siblingFileName = siblingFileName;
        this.expectedResult = expectedResult;
        detector = new MultiPartFileDetector();
    }

    @Test
    public void test() {
        assertEquals(expectedResult, detector.isMultiPartFile(originalFileName, siblingFileName));
    }
}
