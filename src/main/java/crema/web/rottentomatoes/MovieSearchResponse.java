package crema.web.rottentomatoes;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 * A java bean encapsulating a Rotten Tomatoes movie response. 
 * 
 * @author Jason Andersen (andersen.jason@gmail.com)
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class MovieSearchResponse {

    /**
     * URL used to call API to request this object.
     */
    public static final String REQUEST_URL =
            "http://api.rottentomatoes.com/api/public/v1.0/movies/{RT_MOVIE_ID}.json?apikey={RT_API_KEY}";

    @JsonProperty("movies")
    private final List<MovieSearchResult> movies = new LinkedList<MovieSearchResult>();

    /**
     * @return an unmodifiable list of movie results
     */
    public List<MovieSearchResult> getMovieResults() {
        return Collections.unmodifiableList(movies);
    }

    /**
     * A single result within a search result.
     */
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class MovieSearchResult {

        @JsonProperty("id")
        private int movieId;

        @JsonProperty("title")
        private String title;

        @JsonProperty("year")
        private int year;

        @JsonProperty("alternate_ids")
        private Map<String, String> alternateIds;

        public int getMovieId() {
            return movieId;
        }

        public String getTitle() {
            return title;
        }

        public int getYear() {
            return year;
        }

        public Map<String, String> getAlternateIds() {
            return alternateIds;
        }

        @Override
        public String toString() {
            ToStringBuilder builder = new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE);
            builder.append("movieId", movieId);
            builder.append("title", title);
            builder.append("year", year);
            builder.append("alternate_ids", alternateIds);
            return builder.toString();
        }
    }

}

/*
{
    "id": 16867,
    "title": "Snatch",
    "year": 2001,
    "genres": ["Drama", "Action & Adventure", "Mystery & Suspense", "Comedy"],
    "mpaa_rating": "R",
    "runtime": 103,
    "critics_consensus": "Though perhaps a case of style over substance, Guy Ritchie's second crime caper 
    is full of snappy dialogue, dark comedy, and interesting characters.",
    "release_dates": {
        "theater": "2001-01-19",
        "dvd": "2001-07-03"
    },
    "ratings": {
        "critics_rating": "Fresh",
        "critics_score": 73,
        "audience_rating": "Upright",
        "audience_score": 94
    },
    "synopsis": "",
    "posters": {
        "thumbnail": "http://content7.flixster.com/movie/10/93/72/10937277_mob.jpg",
        "profile": "http://content7.flixster.com/movie/10/93/72/10937277_pro.jpg",
        "detailed": "http://content7.flixster.com/movie/10/93/72/10937277_det.jpg",
        "original": "http://content7.flixster.com/movie/10/93/72/10937277_ori.jpg"
    },
    "abridged_cast": [{
        "name": "Benicio Del Toro",
        "id": "162652510",
        "characters": ["Franky Four Fingers"]
    }, {
        "name": "Dennis Farina",
        "id": "162669618",
        "characters": ["Avi"]
    }, {
        "name": "Vinnie Jones",
        "id": "162707683",
        "characters": ["Bullet Tooth Tony"]
    }, {
        "name": "Brad Pitt",
        "id": "162652627",
        "characters": ["Mickey O'Neil"]
    }, {
        "name": "Jason Statham",
        "id": "162653720",
        "characters": ["Turkish"]
    }],
    "abridged_directors": [{
        "name": "Guy Ritchie"
    }],
    "studio": "Columbia Pictures",
    "alternate_ids": {
        "imdb": "0208092"
    },
    "links": {
        "self": "http://api.rottentomatoes.com/api/public/v1.0/movies/16867.json",
        "alternate": "http://www.rottentomatoes.com/m/snatch/",
        "cast": "http://api.rottentomatoes.com/api/public/v1.0/movies/16867/cast.json",
        "clips": "http://api.rottentomatoes.com/api/public/v1.0/movies/16867/clips.json",
        "reviews": "http://api.rottentomatoes.com/api/public/v1.0/movies/16867/reviews.json",
        "similar": "http://api.rottentomatoes.com/api/public/v1.0/movies/16867/similar.json"
    }
}
 */
