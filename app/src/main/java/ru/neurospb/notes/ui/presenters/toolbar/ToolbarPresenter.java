package ru.neurospb.notes.ui.presenters.toolbar;

import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.view.Menu;
import android.view.MenuItem;

import ru.neurospb.notes.R;
import ru.neurospb.notes.ui.presenters.themelist.ThemeListPresenter;
import ru.neurospb.notes.ui.presenters.themelist.IThemeList;
import ru.neurospb.notes.ui.views.StartPoint;
import ru.neurospb.notes.ui.views.toolbar.ToolbarView;

import static ru.neurospb.notes.ui.presenters.base.Ireceiver.BROADCAST_UI;

/**
 * Presenter for Toolbar unit.
 *
 * @author Alexey Utovka
 * @version alpha1
 * @since alpha1
 */

public class ToolbarPresenter implements IToolbar.Presenter {
    private IToolbar.View view;
    private BRtoolbar receiver;
    private IThemeList.Presenter themeList;
    private int stateMode;
    private String stateCurrentTheme;

    //CONSTRUCTOR
    public ToolbarPresenter() {
        view = new ToolbarView();
        receiver = new BRtoolbar(this);
        stateMode = MODE_ROOT;
        stateCurrentTheme = null;
        themeList = new ThemeListPresenter();
    }

    //INTERFACE IMPLEMENTATION: IbasePresenter
    @Override
    public void resume() {
        themeList.resume();
        LocalBroadcastManager.getInstance(StartPoint.getActivity())
                .registerReceiver(receiver, new IntentFilter(BROADCAST_UI));
    }
    @Override
    public void pause() {
        themeList.pause();
        LocalBroadcastManager.getInstance(StartPoint.getActivity())
                .unregisterReceiver(receiver);
    }
    @Override
    public void saveState(Bundle state) {
        state.putInt(STATE_MODE_ID, stateMode);
        state.putString(STATE_CURRENT_THEME_NAME, stateCurrentTheme);
        themeList.saveState(state);
    }
    @Override
    public void loadState(Bundle state) {
        if (state == null) {
            stateMode = MODE_ROOT;
            stateCurrentTheme = null;
        } else {
            stateMode = state.getInt(STATE_MODE_ID, MODE_ROOT);
            stateCurrentTheme = state.getString(STATE_CURRENT_THEME_NAME, null);
        }
        themeList.loadState(state);
    }
    @Override
    public void setState(Bundle newState) {
        if (newState == null) return;
        stateMode = newState.getInt(STATE_MODE_ID, MODE_ROOT);
        stateCurrentTheme = newState.getString(STATE_CURRENT_THEME_NAME, null);
        view.renderMode(stateMode, stateCurrentTheme);
    }

    //INTERFACE IMPLEMENTATION: IToolbar.Presenter
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        boolean isMenuReady = view.prepareMenu(menu);
        view.renderMode(stateMode, stateCurrentTheme);
        return isMenuReady;
    }
    @Override
    public boolean onMenuAction(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                themeList.onUAback();
                return true;
            case R.id.action_newtheme:
                themeList.onUAnewTheme();
                return true;
        }
        return false;
    }
}