package crema.view.controller;

import crema.domain.MediaLibrary;

/**
 * Controller for adding new media libraries.
 * 
 * @author Jason Andersen (andersen.jason@gmail.com)
 * @since Oct 30, 2013
 */
public class AddLibraryController {

    /**
     * Adds a media library
     * @param path
     * @param name
     * @return a populated domain object
     */
    public MediaLibrary addLibrary(String path, String name) {
        return new MediaLibrary();
    }
}
