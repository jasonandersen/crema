package crema.dao.web.rottentomatoes;

import static org.junit.Assert.assertFalse;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

import crema.domain.Movie;
import crema.test.AbstractIntegrationTest;

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
        mockServer = MockRestServiceServer.createServer(restTemplate);
        movie = new Movie();
        movie.setName("Toy Story 3");
        String searchUrl = endpointBuilder.buildSearchUrl(movie.getName());
        String movieUrl = endpointBuilder.buildMovieUrl(770672122);
        mockServer.expect(requestTo(searchUrl)).andRespond(
                withSuccess(json("toy_story_3_search.json"), MediaType.APPLICATION_JSON));
        mockServer.expect(requestTo(movieUrl)).andRespond(
                withSuccess(json("toy_story_3.json"), MediaType.APPLICATION_JSON));

        provider.provideAttributes(movie);
    }

    @Test
    public void test() {
        assertFalse(movie.getAllAttributes().isEmpty());
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
