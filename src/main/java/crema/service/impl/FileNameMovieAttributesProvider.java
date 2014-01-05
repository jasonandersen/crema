package crema.service.impl;

import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import crema.domain.Attribute;
import crema.domain.AttributeSource;
import crema.domain.AttributeType;
import crema.domain.DisplayResolution;
import crema.domain.MediaLibraryNewMovieListener;
import crema.domain.Movie;
import crema.domain.RecordingSource;
import crema.service.MovieAttributesProvider;
import crema.util.text.ReleaseYearMatcher;
import crema.util.text.TokenBoundaryDecorator;
import crema.util.text.TokenDecorator;
import crema.util.text.Tokenizer;
import crema.util.text.WhitespaceCleanerDecorator;

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
    @Autowired
    public FileNameMovieAttributesProvider(
            final TokenBoundaryDecorator tokenBoundaryDecorator,
            final WhitespaceCleanerDecorator whitespaceCleanerDecorator) {
        List<TokenDecorator> decorators = new LinkedList<TokenDecorator>();
        decorators.add(tokenBoundaryDecorator);
        decorators.add(whitespaceCleanerDecorator);
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
        /*
         * Don't evaluate the first token for attributes since that token is presumed to be part of the
         * movie name, not an attribute.
         */
        tokens.remove(0);
        seekRecordingSourceAttribute(movie, tokens);
        seekReleaseYearAttribute(movie, tokens);
        seekDisplayResolutionAttribute(movie, tokens);
    }

    /**
     * Look for tokens that indicate a display resolution.
     * @param movie
     * @param tokens
     */
    private void seekDisplayResolutionAttribute(final Movie movie, final List<String> tokens) {
        for (String token : tokens) {
            for (DisplayResolution resolution : DisplayResolution.values()) {
                if (resolution.matches(token)) {
                    addAttribute(movie, AttributeType.DISPLAY_RESOLUTION, AttributeSource.FILE_NAME, resolution);
                    return;
                }
            }
        }
    }

    /**
     * Looks for tokens that indicate a release year.
     * @param movie
     * @param tokens
     */
    private void seekReleaseYearAttribute(final Movie movie, final List<String> tokens) {
        for (String token : tokens) {
            String yearToken = releaseYearMatcher.matches(token);
            if (yearToken != null) {
                addAttribute(movie, AttributeType.RELEASE_YEAR, AttributeSource.FILE_NAME, yearToken);
                return;
            }
        }
    }

    /**
     * Looks for tokens that indicate a recording source.
     * @param movie
     * @param tokens
     */
    private void seekRecordingSourceAttribute(final Movie movie, final List<String> tokens) {
        for (String token : tokens) {
            for (RecordingSource source : RecordingSource.values()) {
                if (source.matches(token)) {
                    addAttribute(movie, AttributeType.RECORDING_SOURCE, AttributeSource.FILE_NAME, source);
                    return;
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
