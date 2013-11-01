package crema.cucumber;

import org.junit.runner.RunWith;

import cucumber.api.junit.Cucumber;

/**
 * 
 * @author Jason Andersen (andersen.jason@gmail.com)
 * @since Nov 1, 2013
 */
@RunWith(Cucumber.class)
@Cucumber.Options(format = { "pretty", "html:target/cucumber" })
public class CucumberJUnitRunner {
    //noop
}
