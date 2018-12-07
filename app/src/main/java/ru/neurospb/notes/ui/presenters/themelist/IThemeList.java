package ru.neurospb.notes.ui.presenters.themelist;

import ru.neurospb.notes.ui.presenters.base.IbasePresenter;
import ru.neurospb.notes.ui.views.base.IbaseView;
import ru.neurospb.notes.ui.views.base.IviewWithState;
import ru.neurospb.notes.ui.views.themelist.recyclerview.IThemeListItem;

/**
 * An interface for UI unit: List of current theme content.
 *
 * @author Alexey Utovka
 * @version alpha1
 * @since alpha1
 */

public interface IThemeList {
    //VIEW
    interface View extends IbaseView, IviewWithState {
        /**
         * Fires re-render of recyclerview to show up to date data.
         */
        void onListUpdate();
        /**
         * Clears visual selection of list's item , if any.
         */
        void clearSelection();
        /**
         * Gets user selected item's position in recyclerview's adapter
         * @return An int index of user selected item in arraylist
         */
        int getSelectedAdapterPosition();
        /**
         * Gets user selected arraylist's item
         * @return An implementation of IThemeListItem.
         */
        IThemeListItem getSelectedItem();
    }
    //PRESENTER
    interface Presenter extends IbasePresenter {
        //STATE
        String STATE_CURRENT_THEME = "themelist_current_theme";

        /**
         * Requests Core for data to fill the list.
         */
        void requestListData();
        /**
         * User's actions support
         */
        //create a new theme
        void onUAnewTheme();
        //go back to parent theme
        void onUAback();
        //rename user selected theme
        void onUArenameTheme();
        //delete user selected theme
        void onUAdeleteTheme();
        //abort user selection
        void onUAabortSelection();
    }
}