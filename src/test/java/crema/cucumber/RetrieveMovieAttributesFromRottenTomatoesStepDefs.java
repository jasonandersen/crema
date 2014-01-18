package crema.cucumber;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

import crema.domain.AttributeType;
import crema.domain.Movie;
import crema.web.rottentomatoes.EndpointBuilder;
import crema.web.rottentomatoes.RottenTomatoesMovieAttributesProvider;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

/**
 * Step definitions in order to test retrieving movie attributes from rottentomatoes.com.
 * 
 * @author Jason Andersen (andersen.jason@gmail.com)
 */
public class RetrieveMovieAttributesFromRottenTomatoesStepDefs extends AbstractCucumberStepDefs {

    private MockRestServiceServer mockServer;

    @Autowired
    private RottenTomatoesMovieAttributesProvider provider;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private EndpointBuilder endpointBuilder;

    private Movie movie;

    @Before
    public void setupMockRestServer() throws IOException {
        String searchUrl = endpointBuilder.buildSearchUrl("Toy Story 3");
        String movieUrl = endpointBuilder.buildMovieUrl(770672122);
        mockServer = MockRestServiceServer.createServer(restTemplate);
        mockServer.expect(requestTo(searchUrl)).andRespond(
                withSuccess(json("toy_story_3_search.json"), MediaType.APPLICATION_JSON));
        mockServer.expect(requestTo(movieUrl)).andRespond(
                withSuccess(json("toy_story_3.json"), MediaType.APPLICATION_JSON));
    }

    @Given("^that I have a movie that is registered in RottenTomatoes.com named \"([^\"]*)\"$")
    public void that_I_have_a_movie_that_is_registered_in_RottenTomatoes_com_named(String movieName) throws Throwable {
        movie = new Movie();
        movie.setName(movieName);
    }

    @When("^I retrieve RottenTomatoes.com attributes for that movie$")
    public void I_retrieve_RottenTomatoes_com_attributes_for_that_movie() throws Throwable {
        provider.decorateMovie(movie);
    }

    @Then("^the movie's release year is (\\d+)$")
    public void the_movie_s_release_year_is(int year) throws Throwable {
        assertEquals(year, getAttribute(AttributeType.RELEASE_YEAR));
    }

    @Then("^the movie's MPAA rating is \"([^\"]*)\"$")
    public void the_movie_s_MPAA_rating_is(String rating) throws Throwable {
        assertEquals(rating, getAttribute(AttributeType.MPAA_RATING));
    }

    @Then("^the movie's runtime is (\\d+) minutes$")
    public void the_movie_s_runtime_is_minutes(int runtime) throws Throwable {
        assertEquals(runtime, getAttribute(AttributeType.RUNTIME));
    }

    @Then("^the movie's critics' consensus is \"([^\"]*)\"$")
    public void the_movie_s_critics_consensus_is(String consensus) throws Throwable {
        assertEquals(consensus, getAttribute(AttributeType.CRITICS_CONSENSUS));
    }

    @Then("^the movie's critics' score is (\\d+)$")
    public void the_movie_s_critics_score_is(int criticsScore) throws Throwable {
        assertEquals(criticsScore, getAttribute(AttributeType.CRITICS_SCORE));
    }

    @Then("^the movie's audience score is (\\d+)$")
    public void the_movie_s_audience_score_is(int score) throws Throwable {
        assertEquals(score, getAttribute(AttributeType.AUDIENCE_SCORE));
    }

    @Then("^the movie's synopsis is \"([^\"]*)\"$")
    public void the_movie_s_synopsis_is(String synopsis) throws Throwable {
        assertEquals(synopsis, getAttribute(AttributeType.SYNOPSIS));
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
