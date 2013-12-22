package crema.domain;

import java.util.UUID;

/**
 * Domain objects that are identifiable should extend from this class.
 * 
 * @author Jason Andersen (andersen.jason@gmail.com)
 */
public abstract class IdentifiableDomainObject {

    private UUID uuid;

    public UUID getId() {
        return uuid;
    }

    public void setUuid(final UUID uuid) {
        this.uuid = uuid;
    }
}
