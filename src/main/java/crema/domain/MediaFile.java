package crema.domain;

import java.io.File;

import org.apache.commons.lang.Validate;

import crema.exception.MediaFileException;

/**
 * Represents a single media file.
 * 
 * @author Jason Andersen (andersen.jason@gmail.com)
 */
public class MediaFile {

    private static final String EXT_MARKER = ".";

    private static final String TO_STRING = "[%s]baseDirectory=%s;relativePath=%s;";

    private String relativePath;

    private MediaLibrary library;

    /**
     * Constructor.
     * @param mediaLibrary
     * @param file
     * @throws MediaFileException if the file is not a descendant of the media library's base directory
     */
    public MediaFile(final MediaLibrary mediaLibrary, final File file) throws MediaFileException {
        Validate.notNull(mediaLibrary, "Media library cannot be null.");
        Validate.notNull(file, "File cannot be null.");
        library = mediaLibrary;
        relativePath = parseRelativePath(library.getBaseDirectory(), file);
    }

    /**
     * No argument constructor.
     */
    public MediaFile() {
        //no argument constructor to comply with Javabean specifications
    }

    /**
     * @return the name of the actual media file
     */
    public String getFileName() {
        int index = relativePath.lastIndexOf(File.separator);
        return relativePath.substring(index + 1);
    }

    /**
     * @return the name of the actual media file with the extension removed
     */
    public String getFileNameWithoutExtension() {
        String fileName = getFileName();
        int position = fileName.lastIndexOf(EXT_MARKER);
        if (position <= 0) {
            return fileName;
        }
        return fileName.substring(0, position);
    }

    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return String.format(TO_STRING, getClass().getSimpleName(), library.getBaseDirectory(), relativePath);
    }

    /**
     * Determines the path of the file relative to the path of the existing directory.
     * @param existingDirectory
     * @param file
     * @return the path of the file relative to the path of the existing directory
     * @throws MediaFileException 
     */
    private String parseRelativePath(final File existingDirectory, final File file) throws MediaFileException {
        String directoryPath = existingDirectory.getPath();
        String filePath = file.getPath();
        if (!filePath.startsWith(directoryPath)) {
            String message = String.format("Media file \"%s\" is not located within the media library at \"%s\"",
                    filePath, directoryPath);
            throw new MediaFileException(message);
        }
        String relative = filePath.substring(directoryPath.length());
        if (relative.startsWith(File.separator)) {
            relative = relative.substring(File.separator.length());
        }

        return relative;
    }

    /**
     * @return the parent {@MediaLibrary} that this file resides in
     */
    public MediaLibrary getLibrary() {
        return library;
    }

    public void setLibrary(final MediaLibrary library) {
        this.library = library;
    }

    /**
     * @return the path to this file relative to the path of the parent {@link MediaLibrary}'s path
     */
    public String getRelativePath() {
        return relativePath;
    }

    public void setRelativePath(final String relativePath) {
        this.relativePath = relativePath;
    }

}
