package ru.neurospb.notes.ui.presenters.toolbar;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import ru.neurospb.notes.ui.models.Theme;
import ru.neurospb.notes.ui.models.utilities.UIutilityTheme;

import static ru.neurospb.notes.ui.presenters.base.Ireceiver.PARAM_DATA;
import static ru.neurospb.notes.ui.presenters.base.Ireceiver.PARAM_TYPE;
import static ru.neurospb.notes.ui.presenters.base.Ireceiver.TYPE_NAVIGATE_TO;
import static ru.neurospb.notes.ui.presenters.toolbar.IToolbar.Presenter.STATE_CURRENT_THEME_NAME;
import static ru.neurospb.notes.ui.presenters.toolbar.IToolbar.Presenter.STATE_MODE_ID;

/**
 * Broadcast Receiver for Toolbar unit.
 * Listens UI events.
 *
 * @author Alexey Utovka
 * @version alpha1
 * @since alpha1
 */

public class BRtoolbar extends BroadcastReceiver {
    private IToolbar.Presenter toolbar;

    //CONSTRUCTOR
    public BRtoolbar(IToolbar.Presenter toolbar) {
        this.toolbar = toolbar;
    }

    //METHODS IMPLEMENTATION: BroadcastReceiver
    @Override
    public void onReceive(Context context, Intent intent) {
        switch (intent.getIntExtra(PARAM_TYPE, 0)) {
            case TYPE_NAVIGATE_TO:
                Bundle newState = new Bundle();
                Theme theme2go = (Theme) intent.getSerializableExtra(PARAM_DATA);
                newState.putInt(STATE_MODE_ID, UIutilityTheme.getToolbarMode(theme2go));
                newState.putString(STATE_CURRENT_THEME_NAME, theme2go.getName());
                toolbar.setState(newState);
                break;
        }
    }
}