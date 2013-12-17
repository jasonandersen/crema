package crema.view.pivot.window;

import java.io.File;
import java.net.URL;

import org.apache.pivot.beans.BXML;
import org.apache.pivot.beans.Bindable;
import org.apache.pivot.collections.Map;
import org.apache.pivot.collections.Sequence;
import org.apache.pivot.util.Resources;
import org.apache.pivot.wtk.Alert;
import org.apache.pivot.wtk.Button;
import org.apache.pivot.wtk.ButtonPressListener;
import org.apache.pivot.wtk.FileBrowserSheet;
import org.apache.pivot.wtk.Label;
import org.apache.pivot.wtk.MessageType;
import org.apache.pivot.wtk.PushButton;
import org.apache.pivot.wtk.Sheet;
import org.apache.pivot.wtk.SheetCloseListener;
import org.apache.pivot.wtk.Window;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import crema.service.MediaLibraryService;
import crema.util.BeanContext;

/**
 * The primary UI window for Crema.
 * 
 * @author Jason Andersen (andersen.jason@gmail.com)
 */
public class Main extends Window implements Bindable {

    private static Logger log = LoggerFactory.getLogger(Main.class);

    private final MediaLibraryService mediaLibraryService = BeanContext.getBean(MediaLibraryService.class);

    @BXML
    private PushButton addMediaLibraryButton;

    @BXML
    private Label libraryPathLabel;

    /**
     * @see org.apache.pivot.beans.Bindable#initialize(org.apache.pivot.collections.Map, java.net.URL, org.apache.pivot.util.Resources)
     */
    public void initialize(Map<String, Object> namespace, URL location, Resources resources) {
        log.debug("initialize called");
        if (addMediaLibraryButton != null) {
            addMediaLibraryButton.getButtonPressListeners().add(new AddMediaLibraryButtonPressListener());
        }
    }

    /**
     * Handles button press event for add media library button.
     */
    private class AddMediaLibraryButtonPressListener implements ButtonPressListener {
        /**
         * @see org.apache.pivot.wtk.ButtonPressListener#buttonPressed(org.apache.pivot.wtk.Button)
         */
        public void buttonPressed(Button button) {
            FileBrowserSheet.Mode fileBrowserSheetMode = FileBrowserSheet.Mode.SAVE_TO;
            final FileBrowserSheet fileBrowserSheet = new FileBrowserSheet();
            fileBrowserSheet.setMode(fileBrowserSheetMode);
            fileBrowserSheet.open(Main.this, new FileBrowserSheetCloseListener());
        }
    }

    /**
     * Handles sheet closed event for file browser sheet.
     */
    private class FileBrowserSheetCloseListener implements SheetCloseListener {
        /**
         * @see org.apache.pivot.wtk.SheetCloseListener#sheetClosed(org.apache.pivot.wtk.Sheet)
         */
        public void sheetClosed(Sheet sheet) {
            if (sheet.getResult()) {
                FileBrowserSheet fileBrowserSheet = (FileBrowserSheet) sheet;
                log.debug("sheet result found");
                Sequence<File> selectedFiles = fileBrowserSheet.getSelectedFiles();
                String selectedPath = getSelectedPath(selectedFiles);
                libraryPathLabel.setText(selectedPath);
            } else {
                log.debug("no sheet result found");
                Alert.alert(MessageType.INFO, "You didn't select anything.", Main.this);
            }
        }

        private String getSelectedPath(Sequence<File> selectedFiles) {
            File selectedFile = selectedFiles.get(0);
            return selectedFile.getPath();
        }

    }

}
