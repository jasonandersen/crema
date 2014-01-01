package crema.dao.web.rottentomatoes.v1_0;

import java.util.List;
import java.util.Map;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 * A java bean encapsulating a Rotten Tomatoes movie response. 
 * 
 * @author Jason Andersen (andersen.jason@gmail.com)
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class MovieResponse {

    /**
     * URL used to call API to request this object.
     */
    public static final String REQUEST_URL =
            "http://api.rottentomatoes.com/api/public/v1.0/movies/{RT_MOVIE_ID}.json?apikey={RT_API_KEY}";

    /*
     * NOTE: see original JSON response in comments at the bottom of the class
     */

    @JsonProperty("id")
    private int movieId;

    @JsonProperty("title")
    private String title;

    @JsonProperty("year")
    private int year;

    @JsonProperty("genres")
    private List<String> genres;

    @JsonProperty("mpaa_rating")
    private String mpaaRating;

    @JsonProperty("runtime")
    private int runtime;

    @JsonProperty("critics_consensus")
    private String criticsConsensus;

    @JsonProperty("ratings")
    private Map<String, String> ratings;

    @JsonProperty("synopsis")
    private String synopsis;

    @JsonProperty("posters")
    private Map<String, String> posterUrls;

    @JsonProperty("abridged_cast")
    private List<CastMember> abridgedCast;

    @JsonProperty("abridged_directors")
    private List<Director> abridgedDirectors;

    @JsonProperty("alternate_ids")
    private Map<String, String> alternateIds;

    @JsonProperty("studio")
    private String studio;

    @JsonProperty("links")
    private Map<String, String> links;

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(final int movieId) {
        this.movieId = movieId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    public int getYear() {
        return year;
    }

    public void setYear(final int year) {
        this.year = year;
    }

    public String getMpaaRating() {
        return mpaaRating;
    }

    public void setMpaaRating(final String mpaaRating) {
        this.mpaaRating = mpaaRating;
    }

    public String getCriticsConsensus() {
        return criticsConsensus;
    }

    public void setCriticsConsensus(final String criticsConsensus) {
        this.criticsConsensus = criticsConsensus;
    }

    public Map<String, String> getRatings() {
        return ratings;
    }

    public void setRatings(final Map<String, String> ratings) {
        this.ratings = ratings;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(final String synopsis) {
        this.synopsis = synopsis;
    }

    public Map<String, String> getPosterUrls() {
        return posterUrls;
    }

    public void setPosterUrls(final Map<String, String> posterUrls) {
        this.posterUrls = posterUrls;
    }

    public List<CastMember> getAbridgedCast() {
        return abridgedCast;
    }

    public void setAbridgedCast(final List<CastMember> abridgedCast) {
        this.abridgedCast = abridgedCast;
    }

    public Map<String, String> getAlternateIds() {
        return alternateIds;
    }

    public void setAlternateIds(final Map<String, String> alternateIds) {
        this.alternateIds = alternateIds;
    }

    public Map<String, String> getLinks() {
        return links;
    }

    public void setLinks(final Map<String, String> links) {
        this.links = links;
    }

    public List<String> getGenres() {
        return genres;
    }

    public void setGenres(final List<String> genres) {
        this.genres = genres;
    }

    public int getRuntime() {
        return runtime;
    }

    public void setRuntime(final int runtime) {
        this.runtime = runtime;
    }

    public List<Director> getAbridgedDirectors() {
        return abridgedDirectors;
    }

    public void setAbridgedDirectors(final List<Director> abridgedDirectors) {
        this.abridgedDirectors = abridgedDirectors;
    }

    public String getStudio() {
        return studio;
    }

    public void setStudio(final String studio) {
        this.studio = studio;
    }

    /**
     * An individual cast member in a movie and the characters they played.
     */
    @JsonIgnoreProperties(ignoreUnknown = true)
    public class CastMember {

        @JsonProperty("id")
        private int castMemberId;

        @JsonProperty("name")
        private String name;

        @JsonProperty("characters")
        private List<String> characters;

        public int getCastMemberId() {
            return castMemberId;
        }

        public void setCastMemberId(final int castMemberId) {
            this.castMemberId = castMemberId;
        }

        public String getName() {
            return name;
        }

        public void setName(final String name) {
            this.name = name;
        }

        public List<String> getCharacters() {
            return characters;
        }

        public void setCharacters(final List<String> characters) {
            this.characters = characters;
        }
    }

    /**
     * A director of a movie.
     */
    public class Director {

        @JsonProperty("name")
        private String name;

        public String getName() {
            return name;
        }

        public void setName(final String name) {
            this.name = name;
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
