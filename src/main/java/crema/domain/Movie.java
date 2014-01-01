package crema.domain;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * A video-based media file.
 * 
 * @author Jason Andersen (andersen.jason@gmail.com)
 */
public class Movie {

    private List<MediaFile> mediaFiles;

    private String name;

    public Movie() {
        mediaFiles = new LinkedList<MediaFile>();
    }

    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return name;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    /**
     * Adds a single media file to this movie.
     * @param file
     */
    public void addFile(final MediaFile file) {
        mediaFiles.add(file);
    }

    /**
     * @return an unmodifiable list of media files associated with this movie
     */
    public List<MediaFile> getMediaFiles() {
        return Collections.unmodifiableList(mediaFiles);
    }

    /**
     * @return the first media file for this movie
     * @throws IllegalStateException when there are no media files
     */
    public MediaFile getFirstMediaFile() {
        if (mediaFiles.isEmpty()) {
            throw new IllegalStateException("mediaFiles cannot be empty");
        }
        return mediaFiles.get(0);
    }

    /**
     * @return the file path of the _first_ media file for this movie
     * @throws IllegalStateException when there are no media files
     */
    public String getRelativeFilePath() {
        MediaFile firstFile = getFirstMediaFile();
        return firstFile.getRelativePath();
    }

    /**
     * @return true if this movie is split into multiple files
     */
    public boolean hasMultipleParts() {
        return mediaFiles.size() > 1;
    }

    /**
     * @return the total size in bytes of all the media files this movie is comprised of
     */
    public long getTotalSize() {
        long size = 0;
        for (MediaFile file : mediaFiles) {
            size += file.getSize();
        }
        return size;
    }

}
