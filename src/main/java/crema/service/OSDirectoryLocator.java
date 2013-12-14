package crema.service;


/**
 * Provides services to obtain operating system specific directories.
 * 
 * @author Jason Andersen (andersen.jason@gmail.com)
 */
public interface OSDirectoryLocator {

    /**
     * @return a file that represents a user's home directory
     */
    public String getUserHomeDirectoryPath();

}
