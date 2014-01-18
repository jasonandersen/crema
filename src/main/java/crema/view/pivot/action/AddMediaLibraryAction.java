package crema.view.pivot.action;

import java.io.File;

import org.apache.commons.lang.Validate;
import org.apache.pivot.collections.Sequence;
import org.apache.pivot.wtk.Action;
import org.apache.pivot.wtk.FileBrowserSheet;
import org.apache.pivot.wtk.Sheet;
import org.apache.pivot.wtk.SheetCloseListener;
import org.apache.pivot.wtk.Window;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import crema.domain.MediaLibrary;
import crema.exception.CremaException;
import crema.exception.MediaLibraryException;
import crema.service.MediaLibraryService;

/**
 * Action to add a new media library.
 * 
 * @author Jason Andersen (andersen.jason@gmail.com)
 */
@Component
@Scope("prototype")
public class AddMediaLibraryAction extends Action {

    private static Logger log = LoggerFactory.getLogger(AddMediaLibraryAction.class);

    @Autowired
    private MediaLibraryService libraryService;

    private File mediaLibraryFile;

    private MediaLibrary mediaLibrary;

    private Window owner;

    private FileBrowserSheet fileBrowserSheet;

    public AddMediaLibraryAction() {
        fileBrowserSheet = new FileBrowserSheet();
        fileBrowserSheet.setMode(FileBrowserSheet.Mode.SAVE_TO);
    }

    /**
     * @see org.apache.pivot.wtk.Action#perform(org.apache.pivot.wtk.Component)
     */
    @Override
    public void perform(final org.apache.pivot.wtk.Component source) {
        log.debug("perform called");
        Validate.notNull(owner);
        Validate.notNull(fileBrowserSheet);
        fileBrowserSheet.open(owner, new FileBrowserSheetCloseListener());
    }

    /**
     * Set the window owner for this action.
     * @param owner
     */
    public void setOwner(final Window owner) {
        this.owner = owner;
    }

    /**
     * @return the created media library
     */
    public MediaLibrary getMediaLibrary() {
        return mediaLibrary;
    }

    /**
     * Protected setter to allow tests to inject a mock.
     * @param fileBrowserSheet2
     */
    protected void setFileBrowserSheet(final FileBrowserSheet fileBrowserSheet) {
        this.fileBrowserSheet = fileBrowserSheet;
    }

    /**
     * Call the service to create the library.
     * @return the created library
     * @throws CremaException 
     */
    private void createLibrary() throws CremaException {
        String name = "Media Library 2";
        try {
            mediaLibrary = libraryService.createMediaLibrary(mediaLibraryFile, name);
            log.debug("library created: {}", mediaLibrary);
        } catch (MediaLibraryException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Handles sheet closed event for file browser sheet. This will retrieve the directory chosen for
     * a media library.
     */
    private class FileBrowserSheetCloseListener implements SheetCloseListener {
        /**
         * @see org.apache.pivot.wtk.SheetCloseListener#sheetClosed(org.apache.pivot.wtk.Sheet)
         */
        public void sheetClosed(final Sheet sheet) {
            log.debug("file browser sheet sheetClosed event");
            if (sheet.getResult()) {
                Sequence<File> selectedFiles = fileBrowserSheet.getSelectedFiles();
                mediaLibraryFile = selectedFiles.get(0);
                try {
                    createLibrary();
                } catch (CremaException e) {
                    log.error(e.getMessage(), e);
                }
            }
        }
    }

}
