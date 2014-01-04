package crema.util.text;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import crema.domain.Attribute;
import crema.domain.AttributeType;
import crema.domain.MediaLibrary;
import crema.domain.Movie;
import crema.exception.MediaFileException;
import crema.test.TestUtils;

/**
 * Tests the {@link FileNameMovieAttributesProvider} class.
 * 
 * @author Jason Andersen (andersen.jason@gmail.com)
 */
@RunWith(Parameterized.class)
public class FileNameMovieAttributesProviderTest {

    @Parameters(name = "{0} = {1}, {2}, {3}")
    public static Collection<Object[]> buildTestParameters() {
        return Arrays.asList(new Object[][] {
                { "Somers.Town.2008.LIMITED.DVDRip.XviD-DMT.avi", "2008", null, "DVD" },
                { "I'm.Not.There.[2007].DvDrip[Eng]1080p-aXXo.avi", "2007", "1080p", "DVD" },
                { "The.Dark.Knight.Returns.2013.iNTERNAL.720p.BDRi.avi", "2013", "720p", "BlueRay" },
                { "blah.bray.avi", null, null, "BlueRay" },
                { "BLAH.BRAY.AVI", null, null, "BlueRay" },
                /*{ "", "", "", "" },
                { "", "", "", "" },
                { "", "", "", "" },
                { "", "", "", "" },
                { "", "", "", "" },
                { "", "", "", "" },
                { "", "", "", "" },
                { "", "", "", "" },
                { "", "", "", "" },
                { "", "", "", "" },
                { "", "", "", "" },
                { "", "", "", "" },
                { "", "", "", "" }*/
        });
    }

    private FileNameMovieAttributesProvider detailsProvider;

    private Movie movie;

    private String expectedYear;

    private String expectedResolution;

    private String expectedSource;

    /**
     * Constructor.
     * @param fileName
     * @param expectedYear
     * @param expectedResolution
     * @param expectedSource
     * @throws IOException 
     * @throws MediaFileException 
     */
    public FileNameMovieAttributesProviderTest(String fileName, String expectedYear, String expectedResolution,
            String expectedSource) throws IOException, MediaFileException {

        this.expectedResolution = expectedResolution;
        this.expectedSource = expectedSource;
        this.expectedYear = expectedYear;

        File directory = TestUtils.buildTestDirectory(getClass());
        File file = TestUtils.buildFileRelativeToDirectory(directory, fileName);

        MediaLibrary library = new MediaLibrary();
        library.setBaseDirectory(directory);
        library.addMovie(file);

        movie = library.getMovies().get(0);
    }

    @Before
    public void setup() {
        detailsProvider = new FileNameMovieAttributesProvider();
        detailsProvider.setReleaseYearMatcher(new ReleaseYearMatcher());
    }

    @Test
    public void test() {
        detailsProvider.provideAttributes(movie);
        String actualYear = getAttributeValue(AttributeType.RELEASE_YEAR);
        String actualResolution = getAttributeValue(AttributeType.DISPLAY_RESOLUTION);
        String actualSource = getAttributeValue(AttributeType.RECORDING_SOURCE);
        assertEquals(expectedYear, actualYear);
        assertEquals(expectedResolution, actualResolution);
        assertEquals(expectedSource, actualSource);
    }

    private String getAttributeValue(AttributeType type) {
        Attribute attribute = movie.getAttribute(type);
        return attribute == null ? null : attribute.getValue().toString();
    }

}
