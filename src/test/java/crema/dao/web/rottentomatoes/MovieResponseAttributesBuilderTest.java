package crema.dao.web.rottentomatoes;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import crema.domain.Attribute;
import crema.domain.AttributeType;
import crema.domain.AttributesResult;
import crema.domain.AttributesResultNoResultsFound;
import crema.domain.AttributesResultSuccessful;
import crema.domain.Link;

/**
 * Testing the {@link MovieResponseAttributesBuilderTest} class.
 * 
 * @author Jason Andersen (andersen.jason@gmail.com)
 */
public class MovieResponseAttributesBuilderTest {

    private MovieResponse response;

    private MovieResponseAttributesBuilder builder;

    private AttributesResult result;

    @Before
    public void setup() {
        response = new MovieResponse();
    }

    @Test
    public void testNullResponse() {
        response = null;
        run();
        assertTrue(result instanceof AttributesResultNoResultsFound);
    }

    @Test
    public void testEmptyResponse() {
        run();
        assertTrue(result instanceof AttributesResultNoResultsFound);
    }

    @Test
    public void testNonEmptyResponse() {
        response.setSynopsis("I like monkeys");
        run();
        assertTrue(result instanceof AttributesResultSuccessful);
    }

    @Test
    public void testSynopsis() {
        response.setSynopsis("I like monkeys");
        run();
        assertEquals("I like monkeys", getAttribute(AttributeType.SYNOPSIS));
    }

    @Test
    public void testEmptySynopsis() {
        response.setSynopsis("");
        run();
        assertTrue(result instanceof AttributesResultNoResultsFound);
    }

    @Test
    public void testYear() {
        response.setYear(1998);
        run();
        assertEquals(1998, getAttribute(AttributeType.RELEASE_YEAR));
    }

    @Test
    public void testGenres() {
        List<String> genres = Arrays.asList(new String[] {
                "Vampire Puberty Movies",
                "80's Dance Movies set in Burkino Faso",
                "Two Words: Molly Ringwald" });
        response.setGenres(genres);
        run();
        assertSame(genres, getAttribute(AttributeType.GENRES));
    }

    @Test
    public void testMpaaRating() {
        response.setMpaaRating("X");
        run();
        assertEquals("X", getAttribute(AttributeType.MPAA_RATING));
    }

    @Test
    public void testRuntime() {
        response.setRuntime(200);
        run();
        assertEquals(200, getAttribute(AttributeType.RUNTIME));
    }

    @Test
    public void testCriticsConsensus() {
        response.setCriticsConsensus("Holy crap, this was bad on a whole new level. B. A. D.");
        run();
        assertEquals("Holy crap, this was bad on a whole new level. B. A. D.",
                getAttribute(AttributeType.CRITICS_CONSENSUS));
    }

    @Test
    public void testMovieId() {
        response.setMovieId("12345");
        run();
        assertEquals("12345", getAttribute(AttributeType.MOVIE_ID));
    }

    @Test
    public void testRottenTomatoesCriticsScore() {
        Map<String, String> ratings = new HashMap<String, String>();
        ratings.put("critics_score", "99");
        response.setRatings(ratings);
        run();
        assertEquals(99, getAttribute(AttributeType.CRITICS_SCORE));
    }

    @Test
    public void testRottenTomatoesAudienceScore() {
        Map<String, String> ratings = new HashMap<String, String>();
        ratings.put("audience_score", "59");
        response.setRatings(ratings);
        run();
        assertEquals(59, getAttribute(AttributeType.AUDIENCE_SCORE));
    }

    @Test
    public void testImages() {
        Map<String, String> posterUrls = new HashMap<String, String>();
        posterUrls.put("thumbnail", "http://content7.flixster.com/movie/10/93/72/10937277_mob.jpg");
        posterUrls.put("original", "http://content7.flixster.com/movie/10/12/72/10935902_mob.jpg");
        response.setPosterUrls(posterUrls);

        List<Link> expectedImages = new LinkedList<Link>();
        expectedImages.add(new Link("thumbnail", "http://content7.flixster.com/movie/10/93/72/10937277_mob.jpg"));
        expectedImages.add(new Link("original", "http://content7.flixster.com/movie/10/12/72/10935902_mob.jpg"));

        run();

        assertEquals(expectedImages, getAttribute(AttributeType.IMAGES));
    }

    /**
     * @param synopsis
     * @return an attribute of this type, will return null if none are found
     */
    private Object getAttribute(AttributeType type) {
        AttributesResultSuccessful success = (AttributesResultSuccessful) result;
        List<Attribute> attributes = success.getAttributes();
        for (Attribute attribute : attributes) {
            if (attribute.getType().equals(type)) {
                return attribute.getValue();
            }
        }
        return null;
    }

    private void run() {
        builder = new MovieResponseAttributesBuilder(response);
        result = builder.getResult();
    }
}
