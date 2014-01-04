package crema.util.text;

import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import crema.domain.Attribute;
import crema.domain.AttributeSource;
import crema.domain.AttributeType;
import crema.domain.MediaLibraryNewMovieListener;
import crema.domain.Movie;
import crema.domain.RecordingSource;
import crema.service.MovieAttributesProvider;

/**
 * Provides attributes about a movie based on the name of the files.
 * 
 * @author Jason Andersen (andersen.jason@gmail.com)
 */
@Service
public class FileNameMovieAttributesProvider implements MovieAttributesProvider, MediaLibraryNewMovieListener {

    @Autowired
    private ReleaseYearMatcher releaseYearMatcher;

    private final Tokenizer tokenizer;

    /**
     * Constructor.
     */
    public FileNameMovieAttributesProvider() {
        List<TokenDecorator> decorators = new LinkedList<TokenDecorator>();
        decorators.add(new TokenBoundaryDecorator());
        decorators.add(new WhitespaceCleanerDecorator());
        tokenizer = new Tokenizer(decorators);
    }

    /**
     * @see crema.domain.MediaLibraryNewMovieListener#movieAdded(crema.domain.Movie)
     */
    public void movieAdded(final Movie movie) {
        provideAttributes(movie);
    }

    /**
     * @see crema.service.MovieAttributesProvider#provideAttributes(crema.domain.Movie)
     */
    public void provideAttributes(final Movie movie) {
        Validate.notNull(movie);
        String fileName = movie.getFirstMediaFile().getFileNameWithoutExtension();
        List<String> tokens = tokenizer.tokenize(fileName);
        seekRecordingSourceTokens(movie, tokens);
        seekReleaseYearTokens(movie, tokens);

    }

    /**
     * @param movie
     * @param tokens
     */
    private void seekReleaseYearTokens(final Movie movie, final List<String> tokens) {
        for (String token : tokens) {
            String yearToken = releaseYearMatcher.matches(token);
            if (yearToken != null) {
                addAttribute(movie, AttributeType.RELEASE_YEAR, AttributeSource.FILE_NAME, yearToken);
            }
        }
    }

    /**
     * Looks for tokens that indicate a recording source.
     * @param movie
     * @param tokens
     */
    private void seekRecordingSourceTokens(final Movie movie, final List<String> tokens) {
        for (String token : tokens) {
            for (RecordingSource source : RecordingSource.values()) {
                if (source.matches(token)) {
                    addAttribute(movie, AttributeType.RECORDING_SOURCE, AttributeSource.FILE_NAME, source);
                }
            }
        }
    }

    /**
     * Adds a single attribute to a movie.
     * @param movie
     * @param type
     * @param source
     * @param source
     */
    private void addAttribute(final Movie movie, final AttributeType type, final AttributeSource source,
            final Object value) {

        Attribute attribute = new Attribute(type, source, value);
        movie.addAttribute(attribute);
    }

    /**
     * Setter used for testing.
     * @param releaseYearMatcher
     */
    public void setReleaseYearMatcher(final ReleaseYearMatcher releaseYearMatcher) {
        this.releaseYearMatcher = releaseYearMatcher;
    }

}
