package crema.domain;

import java.io.File;

/**
 * A library housing media files.
 * 
 * @author Jason Andersen (andersen.jason@gmail.com)
 */
public class MediaLibrary {

    public static final String FIELD_NAME = "name";

    public static final String FIELD_BASE_DIR = "baseDirectory";

    private File baseDirectory;

    private String name;

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public File getBaseDirectory() {
        return baseDirectory;
    }

    public void setBaseDirectory(final File baseDirectory) {
        this.baseDirectory = baseDirectory;
    }

    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return String.format("[MediaLibrary]name=%s;baseDirectory=%s", name, baseDirectory);
    }

    /**
     * @param targetFile
     * @return true if this file exists within this media library
     */
    public boolean containsFile(final String fileName) {
        return false;
    }

}
