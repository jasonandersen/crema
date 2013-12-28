package crema.view.pivot.window;

import java.net.URL;

import org.apache.pivot.beans.BXML;
import org.apache.pivot.beans.Bindable;
import org.apache.pivot.collections.List;
import org.apache.pivot.collections.Map;
import org.apache.pivot.util.Resources;
import org.apache.pivot.wtk.Button;
import org.apache.pivot.wtk.ButtonPressListener;
import org.apache.pivot.wtk.ListButton;
import org.apache.pivot.wtk.ListButtonSelectionListener;
import org.apache.pivot.wtk.PushButton;
import org.apache.pivot.wtk.TableView;
import org.apache.pivot.wtk.TextInput;
import org.apache.pivot.wtk.Window;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import crema.exception.DuplicateMediaLibraryException;
import crema.util.BeanContext;
import crema.view.pivot.action.AddMediaLibraryAction;
import crema.view.pivot.controller.MainController;
import crema.view.pivot.domain.MediaLibraryView;
import crema.view.pivot.domain.MovieView;

/**
 * The primary UI window for Crema.
 * 
 * @author Jason Andersen (andersen.jason@gmail.com)
 */
public class MainWindow extends Window implements Bindable {

    private static Logger log = LoggerFactory.getLogger(MainWindow.class);

    @BXML
    private PushButton addMediaLibraryButton;
    @BXML
    private ListButton chooseLibraryListButton;
    @BXML
    private TableView moviesTableView;
    @BXML
    private TextInput mediaLibraryNameTextInput;
    @BXML
    private PushButton updateMediaLibraryButton;

    private MainController controller = BeanContext.getBean(MainController.class);

    /**
     * @see org.apache.pivot.beans.Bindable#initialize(
     *      org.apache.pivot.collections.Map, java.net.URL, org.apache.pivot.util.Resources)
     */
    public void initialize(final Map<String, Object> namespace, final URL location, final Resources resources) {
        log.debug("initialize called");
        if (addMediaLibraryButton != null) {
            AddMediaLibraryAction action = BeanContext.getBean(AddMediaLibraryAction.class);
            action.setOwner(this);
            addMediaLibraryButton.setAction(action);
        }
        if (chooseLibraryListButton != null) {
            chooseLibraryListButton.setListData(controller.getAllMediaLibraries());
            chooseLibraryListButton.getListButtonSelectionListeners().add(
                    new ChooseLibraryListButtonSelectionListener());
        }
        if (updateMediaLibraryButton != null) {
            updateMediaLibraryButton.getButtonPressListeners().add(new UpdateMediaLibraryButtonPressListener());
        }
    }

    /**
     * @return the currently selected media library
     */
    private MediaLibraryView getSelectedLibrary() {
        return (MediaLibraryView) chooseLibraryListButton.getSelectedItem();
    }

    /**
     * @return all the movies associated with the currently selected media library
     */
    private List<MovieView> getSelectedLibraryMovies() {
        return getSelectedLibrary().getMovies();
    }

    /**
     * Listener for update media library button.
     */
    private class UpdateMediaLibraryButtonPressListener implements ButtonPressListener {

        /**
         * @see org.apache.pivot.wtk.ButtonPressListener#buttonPressed(org.apache.pivot.wtk.Button)
         */
        public void buttonPressed(final Button button) {
            MediaLibraryView library = getSelectedLibrary();
            String updateText = mediaLibraryNameTextInput.getText();
            if (library == null || updateText == null || updateText.isEmpty()) {
                return;
            }
            library.setName(updateText);
            try {
                controller.updateMediaLibrary(library);
            } catch (DuplicateMediaLibraryException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * Listener for the ChooseLibraryListButton.
     */
    private class ChooseLibraryListButtonSelectionListener extends ListButtonSelectionListener.Adapter {

        /**
         * @see org.apache.pivot.wtk.ListButtonSelectionListener#selectedItemChanged(
         *      org.apache.pivot.wtk.ListButton, java.lang.Object)
         */
        @Override
        public void selectedItemChanged(final ListButton listButton, final Object previousSelectedItem) {
            moviesTableView.setTableData(getSelectedLibraryMovies());
            mediaLibraryNameTextInput.setText(getSelectedLibrary().getName());
        }

    }
}
