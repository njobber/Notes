package ru.neurospb.notes.ui.presenters.themelist;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.LocalBroadcastManager;

import java.util.ArrayList;
import java.util.List;

import ru.neurospb.notes.core.gateways.Icore2ui;
import ru.neurospb.notes.core.models.Folder;
import ru.neurospb.notes.core.usecases.GetFolderContent;
import ru.neurospb.notes.db.controllers.ThemeContentController;
import ru.neurospb.notes.ui.models.Theme;
import ru.neurospb.notes.ui.models.utilities.UIthemePacker;
import ru.neurospb.notes.ui.models.utilities.UIthemeUnpacker;
import ru.neurospb.notes.ui.models.utilities.UIutilityTheme;
import ru.neurospb.notes.ui.views.StartPoint;
import ru.neurospb.notes.ui.views.dialogs.DialogViewsFactory;
import ru.neurospb.notes.ui.views.themelist.recyclerview.IThemeListItem;
import ru.neurospb.notes.ui.views.themelist.ThemeListView;
import ru.neurospb.notes.ui.views.themelist.recyclerview.ThemeItemView;

import static ru.neurospb.notes.ui.presenters.base.Ireceiver.BROADCAST_UI;
import static ru.neurospb.notes.ui.presenters.base.Ireceiver.PARAM_DATA;
import static ru.neurospb.notes.ui.presenters.base.Ireceiver.PARAM_TYPE;
import static ru.neurospb.notes.ui.presenters.base.Ireceiver.TYPE_NAVIGATE_TO;

/**
 * Presenter for ThemeList unit.
 *
 * @author Alexey Utovka
 * @version alpha1
 * @since alpha1
 */

public class ThemeListPresenter implements IThemeList.Presenter,
                                           Icore2ui.Data<List<Folder>>
{
    private IThemeList.View view;
    private BRthemeList receiver;
    private Theme stateCurrentTheme;
    private List<IThemeListItem> items;

    //CONSTRUCTOR
    public ThemeListPresenter() {
        items = new ArrayList<>();
        view = new ThemeListView(items);
        receiver = new BRthemeList(this);
    }

    //INTERFACE IMPLEMENTATION: IbasePresenter
    @Override
    public void resume() {
        LocalBroadcastManager.getInstance(StartPoint.getActivity())
                .registerReceiver(receiver, new IntentFilter(BROADCAST_UI));
        if (items.isEmpty()) requestListData();
    }
    @Override
    public void pause() {
        LocalBroadcastManager.getInstance(StartPoint.getActivity())
                .unregisterReceiver(receiver);
    }
    @Override
    public void saveState(Bundle state) {
        state.putSerializable(STATE_CURRENT_THEME, stateCurrentTheme);
        view.saveState(state);
    }
    @Override
    public void loadState(Bundle state) {
        if (state == null) stateCurrentTheme = new Theme();
        else stateCurrentTheme = (Theme) state.getSerializable(STATE_CURRENT_THEME);
        view.loadState(state);
    }
    @Override
    public void setState(Bundle newState) {
        if (newState == null) return;
        Theme stateNewTheme = (Theme) newState.getSerializable(STATE_CURRENT_THEME);
        if (stateNewTheme == null) return;
        stateCurrentTheme = stateNewTheme;
        requestListData();
    }

    //INTERFACE IMPLEMENTATION: IThemeList.Presenter
    @Override
    public void requestListData() {
        GetFolderContent getFolderContentUseCase = new GetFolderContent(
                new UIthemePacker().pack(stateCurrentTheme),
                this,
                new ThemeContentController()
        );
        getFolderContentUseCase.execute();
    }
    @Override
    public void onUAnewTheme() {
        AHnewTheme actionHandler = new AHnewTheme(view);
        DialogViewsFactory.create(
                DialogViewsFactory.DIALOG_CREATE_NEW_THEME,
                stateCurrentTheme,
                view,
                actionHandler)
                          .show();
    }
    @Override
    public void onUAback() {
        Intent intent = new Intent(BROADCAST_UI);
        intent.putExtra(PARAM_TYPE, TYPE_NAVIGATE_TO);
        intent.putExtra(PARAM_DATA, UIutilityTheme.getParent(stateCurrentTheme));
        LocalBroadcastManager.getInstance(StartPoint.getActivity()).sendBroadcast(intent);
    }
    @Override
    public void onUArenameTheme() {
        int position = view.getSelectedAdapterPosition();
        IThemeListItem item = view.getSelectedItem();
        view.clearSelection();
        if (item instanceof ThemeItemView) {
            AHrenameTheme actionHandler = new AHrenameTheme(view, items, position);
            DialogViewsFactory.create(
                    DialogViewsFactory.DIALOG_RENAME_THEME,
                    (Theme) item.getItemData(),
                    view,
                    actionHandler)
                              .show();
        }
    }
    @Override
    public void onUAdeleteTheme() {
        IThemeListItem item = view.getSelectedItem();
        int position = view.getSelectedAdapterPosition();
        view.clearSelection();
        if (item instanceof ThemeItemView) {
            AHdeleteTheme actionHandler = new AHdeleteTheme(view, items, position);
            DialogViewsFactory.create(
                    DialogViewsFactory.DIALOG_CONFIRM_DELETE,
                    (Theme) item.getItemData(),
                    null,
                    actionHandler)
                              .show();
        }
    }
    @Override
    public void onUAabortSelection() {
        view.clearSelection();
    }

    //INTERFACE IMPLEMENTATION: Icore2ui.Data
    @Override
    public void onDataLoad(@NonNull List<Folder> data) {
        items.clear();
        long lastkey = 0;
        IThemeListItem item;

        for (Folder folder : data) {
            item = new ThemeItemView(
                    new UIthemeUnpacker().pack(folder),
                    lastkey
            );
            items.add(item);
            lastkey++;
        }

        view.onListUpdate();
    }
}