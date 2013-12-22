package crema.domain;

/**
 * A video-based media file.
 * 
 * @author Jason Andersen (andersen.jason@gmail.com)
 */
public class Movie {

    private MediaFile mediaFile;

    public MediaFile getMediaFile() {
        return mediaFile;
    }

    public void setMediaFile(final MediaFile mediaFile) {
        this.mediaFile = mediaFile;
    }

}
