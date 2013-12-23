package crema.domain;

/**
 * A video-based media file.
 * 
 * @author Jason Andersen (andersen.jason@gmail.com)
 */
public class Movie {

    private MediaFile mediaFile;

    private String name;

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public MediaFile getMediaFile() {
        return mediaFile;
    }

    public void setMediaFile(final MediaFile mediaFile) {
        this.mediaFile = mediaFile;
    }

}
