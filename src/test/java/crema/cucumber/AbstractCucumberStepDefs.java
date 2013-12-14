package crema.cucumber;

import org.springframework.test.context.ContextConfiguration;

/**
 * Base class for cucumber step definition classes.
 * 
 * @author Jason Andersen (andersen.jason@gmail.com)
 */
@ContextConfiguration(locations = { "classpath:crema/cucumber/cucumber.xml" })
public abstract class AbstractCucumberStepDefs {

    //noop

}
