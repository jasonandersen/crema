package crema.dao.web.rottentomatoes;

import java.net.URI;
import java.net.URISyntaxException;

import org.apache.commons.lang.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestOperations;

import crema.dao.web.rottentomatoes.MovieSearchResponse.MovieSearchResult;
import crema.domain.AttributeSource;
import crema.domain.AttributesResult;
import crema.domain.AttributesResultException;
import crema.domain.AttributesResultNoResultsFound;
import crema.domain.Movie;
import crema.exception.AttributesProviderException;
import crema.service.MovieAttributesProvider;

/**
 * Provides movie attributes by calling the Rotten Tomatoes REST API. For more information,
 * see the API documentation at:
 * 
 * http://developer.rottentomatoes.com/
 * 
 * @author Jason Andersen (andersen.jason@gmail.com)
 */
@Service
public class RottenTomatoesMovieAttributesProvider implements MovieAttributesProvider {

    private static Logger log = LoggerFactory.getLogger(RottenTomatoesMovieAttributesProvider.class);

    private static final AttributeSource SOURCE = AttributeSource.ROTTEN_TOMATOES;

    @Autowired
    private EndpointBuilder endpointBuilder;

    @Autowired
    private RestOperations restOperations;

    /**
     * @see crema.service.MovieAttributesProvider#provideAttributes(crema.domain.Movie)
     */
    public void provideAttributes(final Movie movie) {
        log.debug("providing attributes for movie: {}", movie);
        if (movie == null) {
            return;
        }
        AttributesResult result = null;
        try {
            MovieSearchResponse searchResponse = searchForMovieRecord(movie);
            if (containsSearchResults(searchResponse)) {
                MovieSearchResult movieSearchResult = chooseClosestMatch(searchResponse);
                MovieResponse movieResponse = retrieveMovieRecord(movieSearchResult.getMovieId());
                result = buildResult(movieResponse);
            } else {
                result = buildEmptyResult();
            }
        } catch (Exception e) {
            result = buildExceptionResult(e);
            log.warn("Exception occurred while providing attributes", e);
        } finally {
            movie.addAttributesResult(result);
        }
    }

    /**
     * @param movie
     * @return search results for a single movie
     * @throws AttributesProviderException
     */
    private MovieSearchResponse searchForMovieRecord(final Movie movie) throws AttributesProviderException {
        String query = movie.getName();
        URI url = buildUri(endpointBuilder.buildSearchUrl(query));
        ResponseEntity<MovieSearchResponse> response = restOperations.exchange(
                url, HttpMethod.GET, null, MovieSearchResponse.class);
        log.debug("search response: {}", response);
        return response.getBody();
    }

    /**
     * @param searchResponse
     * @return true if the search results are non-null and contain search results
     */
    private boolean containsSearchResults(final MovieSearchResponse searchResponse) {
        if (searchResponse == null) {
            return false;
        }
        return !searchResponse.getMovieResults().isEmpty();
    }

    /**
     * @param searchResponse
     * @return the closest match to the movie, will return null if no results were discovered
     */
    private MovieSearchResult chooseClosestMatch(final MovieSearchResponse searchResponse) {
        /*
         * TODO for now, just return the first result. Eventually, we should be doing a confidence 
         * match on the returned values to determine whether we found a match or we have ambiguous
         * results.
         */
        if (searchResponse == null) {
            return null;
        }
        if (searchResponse.getMovieResults().isEmpty()) {
            return null;
        }
        return searchResponse.getMovieResults().get(0);
    }

    /**
     * Calls the Rotten Tomatoes API to retrieve a full record for a movie.
     * @param movieId
     * @return the movie response from the API
     * @throws AttributesProviderException 
     */
    private MovieResponse retrieveMovieRecord(final int movieId) throws AttributesProviderException {
        URI url = buildUri(endpointBuilder.buildMovieUrl(movieId));
        ResponseEntity<MovieResponse> response = restOperations.exchange(
                url, HttpMethod.GET, null, MovieResponse.class);
        log.debug("movie record response: {}", response);
        return response.getBody();

    }

    /**
     * Builds out the attributes result object based on the values returned from the MovieResponse.
     * @param movieResponse
     * @return can return either AttributesResultSuccessful or AttributesResultNoResultsFound
     */
    private AttributesResult buildResult(final MovieResponse movieResponse) {
        Validate.notNull(movieResponse);
        MovieResponseAttributesBuilder attributesBuilder = new MovieResponseAttributesBuilder(movieResponse);
        return attributesBuilder.getResult();
    }

    /**
     * @param url
     * @return a properly constructed URI based on a string URL
     * @throws AttributesProviderException
     */
    private URI buildUri(final String url) throws AttributesProviderException {
        try {
            return new URI(url);
        } catch (URISyntaxException e) {
            throw new AttributesProviderException(e);
        }
    }

    /**
     * @return an {@link AttributesResultNoResults} object indicating no results were found
     */
    private AttributesResult buildEmptyResult() {
        return new AttributesResultNoResultsFound(SOURCE);
    }

    /**
     * @param e
     * @return an {@link AttributesResultException} object to indicate an exception was thrown
     */
    private AttributesResult buildExceptionResult(final Exception e) {
        return new AttributesResultException(SOURCE, e);
    }

}
