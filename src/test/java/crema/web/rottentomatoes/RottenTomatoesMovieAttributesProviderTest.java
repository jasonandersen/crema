package crema.web.rottentomatoes;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

import crema.domain.AttributeType;
import crema.domain.Movie;
import crema.test.AbstractIntegrationTest;
import crema.web.rottentomatoes.EndpointBuilder;
import crema.web.rottentomatoes.RottenTomatoesMovieAttributesProvider;

/**
 * Testing the {@link RottenTomatoesMovieAttributesProvider} class.
 * 
 * @author Jason Andersen (andersen.jason@gmail.com)
 */
@DirtiesContext
public class RottenTomatoesMovieAttributesProviderTest extends AbstractIntegrationTest {

    private MockRestServiceServer mockServer;

    @Autowired
    private RottenTomatoesMovieAttributesProvider provider;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private EndpointBuilder endpointBuilder;

    private Movie movie;

    @Before
    public void setup() throws IOException {
        movie = new Movie();
        movie.setName("Toy Story 3");

        //setup mock REST server
        String searchUrl = endpointBuilder.buildSearchUrl(movie.getName());
        String movieUrl = endpointBuilder.buildMovieUrl(770672122);
        mockServer = MockRestServiceServer.createServer(restTemplate);
        mockServer.expect(requestTo(searchUrl)).andRespond(
                withSuccess(json("toy_story_3_search.json"), MediaType.APPLICATION_JSON));
        mockServer.expect(requestTo(movieUrl)).andRespond(
                withSuccess(json("toy_story_3.json"), MediaType.APPLICATION_JSON));

        provider.decorateMovie(movie);
    }

    @Test
    public void testAttributesFound() {
        assertFalse(movie.getAllAttributes().isEmpty());
    }

    @Test
    public void testMovieId() {
        assertEquals("770672122", getAttribute(AttributeType.MOVIE_ID));
    }

    @Test
    public void testReleaseYear() {
        assertEquals(2010, getAttribute(AttributeType.RELEASE_YEAR));
    }

    @Test
    public void testGenres() {
        List<String> genres = Arrays.asList(new String[] {
                "Animation", "Kids & Family", "Science Fiction & Fantasy", "Comedy"
        });
        assertEquals(genres, getAttribute(AttributeType.GENRES));
    }

    @Test
    public void testMpaaRating() {
        assertEquals("G", getAttribute(AttributeType.MPAA_RATING));
    }

    @Test
    public void testRuntime() {
        assertEquals(103, getAttribute(AttributeType.RUNTIME));
    }

    @Test
    public void testCriticsConsensus() {
        String expected = "Deftly blending comedy, adventure, and honest emotion, Toy Story 3 is a rare second sequel that really works.";
        assertEquals(expected, getAttribute(AttributeType.CRITICS_CONSENSUS));
    }

    @Test
    public void testCriticsScore() {
        assertEquals(99, getAttribute(AttributeType.CRITICS_SCORE));
    }

    @Test
    public void testAudienceScore() {
        assertEquals(89, getAttribute(AttributeType.AUDIENCE_SCORE));
    }

    @Test
    public void testSynopsis() {
        String expected = "Pixar returns to their first success with Toy Story 3. The movie begins with Andy leaving for college"
                +
                " and donating his beloved toys -- including Woody (Tom Hanks) and Buzz (Tim Allen) -- to a daycare. While the crew meets new friends, "
                +
                "including Ken (Michael Keaton), they soon grow to hate their new surroundings and plan an escape. The film was directed by Lee Unkrich "
                +
                "from a script co-authored by Little Miss Sunshine scribe Michael Arndt. ~ Perry Seibert, Rovi";
        assertEquals(expected, getAttribute(AttributeType.SYNOPSIS));
    }

    /**
     * @param attributeType
     * @return
     */
    private Object getAttribute(AttributeType attributeType) {
        return movie.getAttribute(attributeType).getValue();
    }

    /**
     * Loads a test JSON response from a file in the classpath.
     * @param path
     * @return the contents of the file in a string
     * @throws IOException 
     */
    private String json(String fileName) throws IOException {
        ClassPathResource resource = new ClassPathResource(fileName);
        File file = resource.getFile();
        String contents = FileUtils.readFileToString(file);
        return contents;
    }
}
