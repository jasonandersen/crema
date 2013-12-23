package crema.service.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import crema.domain.Movie;
import crema.service.MovieNameService;
import crema.util.tokenize.MovieNameTokenizer;

/**
 * Implementation of {@link MovieNameService}.
 * 
 * @author Jason Andersen (andersen.jason@gmail.com)
 */
@Service
public class MovieNameServiceImpl implements MovieNameService {

    private static Logger log = LoggerFactory.getLogger(MovieNameServiceImpl.class);

    @Autowired
    private MovieNameTokenizer tokenizer;

    /**
     * @see crema.service.MovieNameService#guessName(crema.domain.Movie)
     */
    public String guessName(final Movie movie) {
        String fileName = movie.getMediaFile().getFileName();
        List<String> tokens = tokenizer.tokenize(fileName);
        correctCase(tokens);
        String movieName = assembleTokens(tokens);
        log.debug("file name {} movie name {}", fileName, movieName);
        return movieName;
    }

    /**
     * Changes the case on all the tokens to capitalized.
     * @param tokens
     */
    private void correctCase(final List<String> tokens) {
        for (String token : tokens) {
            if (tokens.isEmpty()) {
                continue;
            }
            token = token.toLowerCase();
            token = StringUtils.capitalize(token);
        }
    }

    /**
     * Assembles the tokens separated by a single space each.
     * @param tokens
     * @return the assembled string
     */
    private String assembleTokens(final List<String> tokens) {
        StringBuilder out = new StringBuilder();
        for (String token : tokens) {
            out.append(token).append(" ");
        }
        return out.toString().trim();
    }

}
