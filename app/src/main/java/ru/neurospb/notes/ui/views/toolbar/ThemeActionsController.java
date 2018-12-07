package ru.neurospb.notes.ui.views.toolbar;

import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;

import ru.neurospb.notes.R;
import ru.neurospb.notes.ui.views.StartPoint;

import static ru.neurospb.notes.ui.presenters.base.Ireceiver.BROADCAST_UI;
import static ru.neurospb.notes.ui.presenters.base.Ireceiver.PARAM_TYPE;
import static ru.neurospb.notes.ui.presenters.base.Ireceiver.TYPE_CLEAR_ITEM_SELECTION;
import static ru.neurospb.notes.ui.presenters.base.Ireceiver.TYPE_THEME_DELETE;
import static ru.neurospb.notes.ui.presenters.base.Ireceiver.TYPE_THEME_RENAME;

/**
 * An implementation of ActionMode.Callback for extended actions on selected Theme.
 *
 * @author Alexey Utovka
 * @version alpha1
 * @since alpha1
 */

public class ThemeActionsController implements ActionMode.Callback {
    //INTERFACE IMPLEMENTATION: ActionMode.Callback
    @Override
    public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
        actionMode.getMenuInflater().inflate(R.menu.menu_actionmode_theme, menu);
        return true;
    }
    @Override
    public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
        return false;
    }
    @Override
    public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
        Intent intent;
        switch (menuItem.getItemId()) {
            case R.id.action_deletetheme:
                intent = new Intent(BROADCAST_UI);
                intent.putExtra(PARAM_TYPE, TYPE_THEME_DELETE);
                LocalBroadcastManager.getInstance(StartPoint.getActivity()).sendBroadcast(intent);
                return true;
            case R.id.action_renametheme:
                intent = new Intent(BROADCAST_UI);
                intent.putExtra(PARAM_TYPE, TYPE_THEME_RENAME);
                LocalBroadcastManager.getInstance(StartPoint.getActivity()).sendBroadcast(intent);
                return true;
        }
        return false;
    }
    @Override
    public void onDestroyActionMode(ActionMode actionMode) {
        Intent intent = new Intent(BROADCAST_UI);
        intent.putExtra(PARAM_TYPE, TYPE_CLEAR_ITEM_SELECTION);
        LocalBroadcastManager.getInstance(StartPoint.getActivity()).sendBroadcast(intent);
    }
}