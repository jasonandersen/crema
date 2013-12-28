package crema.view.pivot.controller;

import org.apache.commons.lang.Validate;
import org.apache.pivot.collections.LinkedList;
import org.apache.pivot.collections.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import crema.domain.MediaLibrary;
import crema.exception.DuplicateMediaLibraryException;
import crema.service.MediaLibraryService;
import crema.view.pivot.domain.MediaLibraryView;

/**
 * PUT COMMENTS HERE WHEN I FIGURE OUT WHAT THIS THING IS GOING TO BE!
 * 
 * @author Jason Andersen (andersen.jason@gmail.com)
 */
@Controller
public class MainController {

    @Autowired
    private MediaLibraryService mediaLibraryService;

    private List<MediaLibraryView> mediaLibraries;

    /**
     * @return all MediaLibrary objects
     */
    public List<MediaLibraryView> getAllMediaLibraries() {
        if (mediaLibraries == null) {
            mediaLibraries = loadMediaLibraries();
        }
        return mediaLibraries;
    }

    /**
     * @return lazy-loaded collection of media libraries
     */
    private List<MediaLibraryView> loadMediaLibraries() {
        List<MediaLibraryView> libraries = new LinkedList<MediaLibraryView>();
        for (MediaLibrary library : mediaLibraryService.getAllMediaLibraries()) {
            libraries.add(new MediaLibraryView(library));
        }
        return libraries;
    }

    /**
     * Updates the properties on the media library.
     * @param libraryView
     * @throws DuplicateMediaLibraryException 
     */
    public void updateMediaLibrary(final MediaLibraryView libraryView) throws DuplicateMediaLibraryException {
        Validate.notNull(libraryView);
        MediaLibrary library = libraryView.getMediaLibrary();
        mediaLibraryService.updateMediaLibrary(library);
    }
}
