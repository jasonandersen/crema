package crema.domain;

import org.apache.commons.lang.builder.EqualsBuilder;

/**
 * Describes a link and it's relationship to it's parent entity.
 * 
 * @author Jason Andersen (andersen.jason@gmail.com)
 */
public class Link {

    private final String rel;

    private final String url;

    /**
     * Constructor.
     * @param rel
     * @param url
     */
    public Link(final String rel, final String url) {
        this.rel = rel;
        this.url = url;
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((rel == null) ? 0 : rel.hashCode());
        result = prime * result + ((url == null) ? 0 : url.hashCode());
        return result;
    }

    /**
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(final Object obj) {
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Link)) {
            return false;
        }
        Link that = (Link) obj;
        EqualsBuilder builder = new EqualsBuilder();
        builder.append(this.rel, that.rel);
        builder.append(this.url, that.url);
        return builder.isEquals();
    }

    public String getRel() {
        return rel;
    }

    public String getUrl() {
        return url;
    }

}
