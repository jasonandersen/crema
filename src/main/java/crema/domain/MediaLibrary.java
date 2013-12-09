package crema.domain;

import java.io.File;

/**
 * A library housing media files.
 * 
 * @author Jason Andersen (andersen.jason@gmail.com)
 */
public class MediaLibrary {

    private File baseDirectory;

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public File getBaseDirectory() {
        return baseDirectory;
    }

    public void setBaseDirectory(File baseDirectory) {
        this.baseDirectory = baseDirectory;
    }

    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return String.format("[MediaLibrary]name=%s;baseDirectory=%s", name, baseDirectory);
    }

}
