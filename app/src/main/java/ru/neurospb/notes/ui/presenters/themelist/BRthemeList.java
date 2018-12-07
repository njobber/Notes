package ru.neurospb.notes.ui.presenters.themelist;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import static ru.neurospb.notes.ui.presenters.base.Ireceiver.PARAM_DATA;
import static ru.neurospb.notes.ui.presenters.base.Ireceiver.PARAM_TYPE;
import static ru.neurospb.notes.ui.presenters.base.Ireceiver.TYPE_CLEAR_ITEM_SELECTION;
import static ru.neurospb.notes.ui.presenters.base.Ireceiver.TYPE_NAVIGATE_TO;
import static ru.neurospb.notes.ui.presenters.base.Ireceiver.TYPE_THEME_DELETE;
import static ru.neurospb.notes.ui.presenters.base.Ireceiver.TYPE_THEME_RENAME;
import static ru.neurospb.notes.ui.presenters.themelist.IThemeList.Presenter.STATE_CURRENT_THEME;

/**
 * Broadcast Receiver for ThemeList unit.
 * Listens UI events.
 *
 * @author Alexey Utovka
 * @version alpha1
 * @since alpha1
 */

public class BRthemeList extends BroadcastReceiver {
    private IThemeList.Presenter themeList;

    //CONSTRUCTOR
    public BRthemeList(IThemeList.Presenter themeList) {
        this.themeList = themeList;
    }

    //IMPLEMENTATION: BroadcastReceiver
    @Override
    public void onReceive(Context context, Intent intent) {
        switch (intent.getIntExtra(PARAM_TYPE, 0)) {
            case TYPE_NAVIGATE_TO:
                Bundle newState = new Bundle();
                newState.putSerializable(STATE_CURRENT_THEME, intent.getSerializableExtra(PARAM_DATA));
                themeList.setState(newState);
                break;
            case TYPE_CLEAR_ITEM_SELECTION:
                themeList.onUAabortSelection();
                break;
            case TYPE_THEME_RENAME:
                themeList.onUArenameTheme();
                break;
            case TYPE_THEME_DELETE:
                themeList.onUAdeleteTheme();
                break;
        }
    }
}