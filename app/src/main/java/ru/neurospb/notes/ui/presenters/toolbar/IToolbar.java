package ru.neurospb.notes.ui.presenters.toolbar;

import android.view.Menu;
import android.view.MenuItem;

import ru.neurospb.notes.ui.presenters.base.IbasePresenter;

/**
 * An interface for UI unit: Toolbar.
 *
 * @author Alexey Utovka
 * @version alpha1
 * @since alpha1
 */

public interface IToolbar {
    //VIEW
    interface View {
        /**
         * Prepares toolbar's (options) menu.
         * Should be run in onCreateOptionsMenu callback of Activity.
         * @return TRUE - if menu prepared successfully.
         */
        boolean prepareMenu(Menu menu);
        /**
         * Shows proper toolbar's layout (menu of actions).
         * @param mode An id of toolbar's mode.
         * @param themeName A name of parent theme OR null if parent is root.
         */
        void renderMode(int mode, String themeName);
    }

    //PRESENTER
    interface Presenter extends IbasePresenter {
        //IDs of toolbar modes
        int MODE_ROOT = 100;
        int MODE_THEME = 102;
        //STATE
        String STATE_MODE_ID = "toolbar_mode_id";
        String STATE_CURRENT_THEME_NAME = "toolbar_current_theme";

        /**
         * Activity's callback support for Options Menu.
         * Should be run in onCreateOptionsMenu callback of Activity.
         * @return TRUE - if menu prepared successfully.
         */
        boolean onCreateOptionsMenu(Menu menu);
        /**
         * Activity's callback support for Options Menu.
         * Should be run in onOptionsItemSelected callback of Activity.
         * Toolbar's buttons/menu listener.
         * @param item Toolbar menu's item.
         * @return This boolean value has to return to Activity's callback onOptionsItemSelected.
         *         TRUE - if onClick menu item processed successfully.
         *         FALSE - if failed
         */
        boolean onMenuAction(MenuItem item);
    }
}