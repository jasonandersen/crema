package crema.domain;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

/**
 * A video-based media file.
 * 
 * @author Jason Andersen (andersen.jason@gmail.com)
 */
public class Movie implements HasName {

    public static final String FIELD_ID = "id";

    private List<MediaFile> mediaFiles;

    private UUID id;

    private String name;

    private final Attributes attributes;

    /**
     * Constructor.
     */
    public Movie() {
        id = UUID.randomUUID();
        mediaFiles = new LinkedList<MediaFile>();
        attributes = new Attributes();
    }

    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return String.format("%s [%s]", name, id);
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    /**
     * @return unique identifier for this movie object
     */
    public UUID getId() {
        return id;
    }

    public void setId(final UUID id) {
        this.id = id;
    }

    /**
     * Adds a single media file to this movie.
     * @param file
     */
    public void addFile(final MediaFile file) {
        mediaFiles.add(file);
    }

    /**
     * @return parent media library this movie belongs to
     */
    public MediaLibrary getMediaLibrary() {
        return getFirstMediaFile().getLibrary();
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

    /**
     * @param type
     * @return an attribute of the specified type, will return null if no attributes exist of that type
     */
    public Attribute getAttribute(final AttributeType type) {
        return attributes.getAttribute(type);
    }

    /**
     * Adds a single attribute to this movie.
     * @param attribute
     */
    public void addAttribute(final Attribute attribute) {
        attributes.addAttribute(attribute);
    }
}
