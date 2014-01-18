package crema.dao.web.rottentomatoes;

import java.net.URI;
import java.net.URLEncoder;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestOperations;

import crema.domain.Attribute;
import crema.domain.AttributeSource;
import crema.domain.AttributeType;
import crema.domain.AttributesResult;
import crema.domain.AttributesResultException;
import crema.domain.AttributesResultNoResultsFound;
import crema.domain.AttributesResultSuccessful;
import crema.domain.Link;
import crema.domain.Movie;
import crema.service.MovieDecorator;

/**
 * This is demonstration code only. This is a fantastic example of what crappy code
 * looks like. This code should never (EVER) be actually used. This is the result of
 * refactoring (more like DEfactoring) the {@link RottenTomatoesMovieAttributesProvider}
 * class into a steaming hot mess. I want to demonstrate the difference between code
 * that works and code that both works and also is readable and maintainable.
 * 
 * @author Jason Andersen (andersen.jason@gmail.com)
 */
@Deprecated
public class RottenTomatoesMovieAttributesProvider2 implements MovieDecorator {

    private static Logger log = LoggerFactory.getLogger(RottenTomatoesMovieAttributesProvider.class);

    @Autowired
    private RestOperations restOperations;

    /**
     * @see crema.service.MovieDecorator#decorateMovie(crema.domain.Movie)
     */
    public void decorateMovie(final Movie movie) {
        if (movie == null) {
            return;
        }
        AttributesResult res = null;
        try {
            URI surl = new URI("http://api.rottentomatoes.com/api/public/v1.0/movies.json?q="
                    + URLEncoder.encode(movie.getName().toLowerCase(), "UTF-8")
                    + "&page_limit=10&page=1&apikey=sp6wwt968extpfnpd4kx97pc");
            MovieSearchResponse sResp = (restOperations.exchange(
                    surl, HttpMethod.GET, null, MovieSearchResponse.class)).getBody();
            if (sResp != null && !sResp.getMovieResults().isEmpty()) {
                int mId = sResp.getMovieResults().get(0).getMovieId();
                if (mId > 0) {
                    URI mrurl = new URI("http://api.rottentomatoes.com/api/public/v1.0/movies/" + mId
                            + ".json?apikey=sp6wwt968extpfnpd4kx97pc");
                    MovieResponse mResp = restOperations.exchange(
                            mrurl, HttpMethod.GET, null, MovieResponse.class).getBody();
                    if (mResp != null) {
                        List<Attribute> attr = new LinkedList<Attribute>();
                        Map<String, String> rtmap = mResp.getRatings();
                        if (rtmap != null) {
                            String cscr = rtmap.get("critics_score");
                            if (cscr != null && !cscr.isEmpty()) {
                                Integer scr = null;
                                if (StringUtils.isNumeric(cscr)) {
                                    scr = Integer.parseInt(cscr);
                                    if (scr != null) {
                                        attr.add(new Attribute(AttributeType.CRITICS_SCORE,
                                                AttributeSource.ROTTEN_TOMATOES, scr));
                                    }
                                }
                            }
                        }
                        rtmap = mResp.getRatings();
                        if (rtmap != null) {
                            String ascr = rtmap.get("audience_score");
                            if (ascr != null && !ascr.isEmpty()) {
                                Integer scr = null;
                                if (StringUtils.isNumeric(ascr)) {
                                    scr = Integer.parseInt(ascr);
                                    if (scr != null) {
                                        attr.add(new Attribute(AttributeType.AUDIENCE_SCORE,
                                                AttributeSource.ROTTEN_TOMATOES, scr));
                                    }
                                }
                            }
                        }
                        Map<String, String> thmbs = mResp.getPosterUrls();
                        if (thmbs != null) {
                            if (!thmbs.isEmpty()) {
                                List<Link> lnk = new LinkedList<Link>();
                                for (String key : thmbs.keySet()) {
                                    lnk.add(new Link(key, thmbs.get(key)));
                                }
                                attr.add(new Attribute(AttributeType.IMAGES, AttributeSource.ROTTEN_TOMATOES, lnk));
                            }
                        }
                        String id = mResp.getMovieId();
                        if (id != null && !id.isEmpty()) {
                            attr.add(new Attribute(AttributeType.MOVIE_ID, AttributeSource.ROTTEN_TOMATOES, id));
                        }
                        String synopsis = mResp.getSynopsis();
                        if (synopsis != null && !synopsis.isEmpty()) {
                            attr.add(new Attribute(AttributeType.SYNOPSIS, AttributeSource.ROTTEN_TOMATOES, synopsis));
                        }
                        int year = mResp.getYear();
                        if (year > 0) {
                            attr.add(new Attribute(AttributeType.RELEASE_YEAR, AttributeSource.ROTTEN_TOMATOES, year));
                        }
                        List<String> genres = mResp.getGenres();
                        if (genres != null && !genres.isEmpty()) {
                            attr.add(new Attribute(AttributeType.GENRES, AttributeSource.ROTTEN_TOMATOES, genres));
                        }
                        String rating = mResp.getMpaaRating();
                        if (rating != null && !rating.isEmpty()) {
                            attr.add(new Attribute(AttributeType.MPAA_RATING, AttributeSource.ROTTEN_TOMATOES, rating));
                        }
                        int runtime = mResp.getRuntime();
                        if (runtime > 0) {
                            attr.add(new Attribute(AttributeType.RUNTIME, AttributeSource.ROTTEN_TOMATOES, runtime));
                        }
                        String criticsConsensus = mResp.getCriticsConsensus();
                        if (criticsConsensus != null && !criticsConsensus.isEmpty()) {
                            attr.add(new Attribute(AttributeType.CRITICS_CONSENSUS, AttributeSource.ROTTEN_TOMATOES,
                                    criticsConsensus));
                        }

                        if (!attr.isEmpty()) {
                            res = new AttributesResultSuccessful(AttributeSource.ROTTEN_TOMATOES, attr);
                        } else {
                            res = new AttributesResultNoResultsFound(AttributeSource.ROTTEN_TOMATOES);
                        }
                    }
                }
            } else {
                res = new AttributesResultNoResultsFound(AttributeSource.ROTTEN_TOMATOES);
            }
        } catch (Exception e) {
            res = new AttributesResultException(AttributeSource.ROTTEN_TOMATOES, e);
            log.warn("Exception occurred while providing attributes", e);
        } finally {
            movie.addAttributesResult(res);
        }
    }
}
