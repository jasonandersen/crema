package crema.domain;

import java.util.UUID;

/**
 * Domain objects that are identifiable should extend from this class.
 * 
 * @author Jason Andersen (andersen.jason@gmail.com)
 */
public abstract class IdentifiableDomainObject {

    private UUID uuid;

    /**
     * @return
     */
    public UUID getId() {
        return uuid;
    }
}
