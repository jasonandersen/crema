package crema.dao.web.rottentomatoes;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import crema.domain.Attribute;
import crema.domain.AttributeSource;
import crema.domain.AttributeType;
import crema.domain.AttributesResult;
import crema.domain.AttributesResultNoResultsFound;
import crema.domain.AttributesResultSuccessful;
import crema.domain.Link;

/**
 * Builds out an {@link AttributesResult} object based on the state of a {@link MovieResponse} object.
 * 
 * @author Jason Andersen (andersen.jason@gmail.com)
 */
public class MovieResponseAttributesMapper {

    private static final String AUDIENCE_SCORE = "audience_score";

    private static final String CRITICS_SCORE = "critics_score";

    private static final AttributeSource SOURCE = AttributeSource.ROTTEN_TOMATOES;

    private final MovieResponse response;

    private final AttributesResult result;

    /**
     * Constructor.
     * @param response
     */
    public MovieResponseAttributesMapper(final MovieResponse response) {
        this.response = response;
        this.result = buildResult();
    }

    /**
     * @return the attributes result object based on the response
     */
    private AttributesResult buildResult() {
        List<Attribute> attributes = findAttributes();
        if (attributes.isEmpty()) {
            return new AttributesResultNoResultsFound(SOURCE);
        }
        return new AttributesResultSuccessful(SOURCE, attributes);
    }

    /**
     * @return a list of all attributes found, will never return null but can return an empty list if
     *      no attributes are found
     */
    private List<Attribute> findAttributes() {
        List<Attribute> attributes = new LinkedList<Attribute>();
        if (response != null) {
            movieId(attributes);
            synopsis(attributes);
            year(attributes);
            genres(attributes);
            mpaaRating(attributes);
            runtime(attributes);
            criticsConsensus(attributes);
            criticsScore(attributes);
            audienceScore(attributes);
            images(attributes);
        }
        return attributes;
    }

    /**
     * @param attributes
     */
    private void images(final List<Attribute> attributes) {
        Map<String, String> thumbnails = response.getPosterUrls();
        if (thumbnails != null) {
            if (!thumbnails.isEmpty()) {
                List<Link> links = new LinkedList<Link>();
                for (String key : thumbnails.keySet()) {
                    links.add(new Link(key, thumbnails.get(key)));
                }
                attributes.add(new Attribute(AttributeType.IMAGES, SOURCE, links));
            }
        }
    }

    /**
     * @param attributes
     */
    private void audienceScore(final List<Attribute> attributes) {
        retrieveScore(attributes, AUDIENCE_SCORE, AttributeType.AUDIENCE_SCORE);
    }

    /**
     * @param attributes
     */
    private void criticsScore(final List<Attribute> attributes) {
        retrieveScore(attributes, CRITICS_SCORE, AttributeType.CRITICS_SCORE);
    }

    /**
     * Retrieves a ratings score from the ratings map if available and adds it as an attribute.
     * @param attributes
     */
    private void retrieveScore(final List<Attribute> attributes, final String key, final AttributeType type) {
        Map<String, String> ratings = response.getRatings();
        if (ratings != null) {
            String criticsScore = ratings.get(key);
            if (criticsScore != null && !criticsScore.isEmpty()) {
                Integer score = parseInt(criticsScore);
                if (score != null) {
                    attributes.add(new Attribute(type, SOURCE, score));
                }
            }
        }
    }

    /**
     * @param attributes
     */
    private void movieId(final List<Attribute> attributes) {
        String id = response.getMovieId();
        if (id != null && !id.isEmpty()) {
            attributes.add(new Attribute(AttributeType.MOVIE_ID, SOURCE, id));
        }
    }

    /**
     * @param attributes
     */
    private void criticsConsensus(final List<Attribute> attributes) {
        String criticsConsensus = response.getCriticsConsensus();
        if (criticsConsensus != null && !criticsConsensus.isEmpty()) {
            attributes.add(new Attribute(AttributeType.CRITICS_CONSENSUS, SOURCE, criticsConsensus));
        }
    }

    /**
     * @param attributes
     */
    private void runtime(final List<Attribute> attributes) {
        int runtime = response.getRuntime();
        if (runtime > 0) {
            attributes.add(new Attribute(AttributeType.RUNTIME, SOURCE, runtime));
        }
    }

    /**
     * @param attributes
     */
    private void mpaaRating(final List<Attribute> attributes) {
        String rating = response.getMpaaRating();
        if (rating != null && !rating.isEmpty()) {
            attributes.add(new Attribute(AttributeType.MPAA_RATING, SOURCE, rating));
        }
    }

    /**
     * @param attributes
     */
    private void genres(final List<Attribute> attributes) {
        List<String> genres = response.getGenres();
        if (genres != null && !genres.isEmpty()) {
            attributes.add(new Attribute(AttributeType.GENRES, SOURCE, genres));
        }
    }

    /**
     * @param attributes
     */
    private void year(final List<Attribute> attributes) {
        int year = response.getYear();
        if (year > 0) {
            attributes.add(new Attribute(AttributeType.RELEASE_YEAR, SOURCE, year));
        }
    }

    /**
     * @param attributes
     */
    private void synopsis(final List<Attribute> attributes) {
        String synopsis = response.getSynopsis();
        if (synopsis != null && !synopsis.isEmpty()) {
            attributes.add(new Attribute(AttributeType.SYNOPSIS, SOURCE, synopsis));
        }
    }

    /**
     * @param string
     * @return a parsed integer from a string, will return null if the string is not numeric
     */
    private Integer parseInt(final String string) {
        if (string == null) {
            return null;
        }
        if (!StringUtils.isNumeric(string)) {
            return null;
        }
        try {
            return Integer.parseInt(string);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * @return the result of attributes pulled from the movie response
     */
    public AttributesResult getResult() {
        return result;
    }

}
