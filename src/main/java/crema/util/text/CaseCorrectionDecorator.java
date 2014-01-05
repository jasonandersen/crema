package crema.util.text;

import java.util.List;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

/**
 * Capitalizes case on tokens.
 * 
 * @author Jason Andersen (andersen.jason@gmail.com)
 */
@Component
public class CaseCorrectionDecorator implements TokenDecorator {

    private static final Pattern ROMAN_NUMERAL = Pattern.compile("^(i|v|x){1,4}$", Pattern.CASE_INSENSITIVE);

    /**
     * @see crema.util.text.TokenDecorator#decorate(java.util.List)
     */
    public void decorate(final List<String> tokens) {
        for (int index = 0; index < tokens.size(); index++) {
            String capitalizedToken = capitalizeToken(tokens.get(index));
            tokens.set(index, capitalizedToken);
        }
    }

    /**
     * @param string
     * @return the token properly capitalized
     */
    private String capitalizeToken(final String token) {
        String capitalizedToken;
        if (isRomanNumeral(token)) {
            capitalizedToken = token.toUpperCase();
        } else {
            capitalizedToken = token.toLowerCase();
            capitalizedToken = StringUtils.capitalize(capitalizedToken);
        }
        return capitalizedToken;
    }

    /**
     * @param token
     * @return true if this token represents a Roman numeral
     */
    private boolean isRomanNumeral(final String token) {
        return ROMAN_NUMERAL.matcher(token).matches();
    }

}
