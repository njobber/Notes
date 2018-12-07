package ru.neurospb.notes.ui.presenters.themelist;

import java.util.List;

import ru.neurospb.notes.R;
import ru.neurospb.notes.core.gateways.Icore2ui;
import ru.neurospb.notes.core.models.Folder;
import ru.neurospb.notes.ui.views.StartPoint;
import ru.neurospb.notes.ui.views.themelist.recyclerview.IThemeListItem;

/**
 * Handler for action: delete selected theme.
 *
 * @author Alexey Utovka
 * @version alpha1
 * @since alpha1
 */

public class AHdeleteTheme implements Icore2ui.Events<Folder>{
    private IThemeList.View view;
    private List<IThemeListItem> items;
    private int itemAdapterPosition;

    //CONSTRUCTOR
    AHdeleteTheme(IThemeList.View view, List<IThemeListItem> items, int itemAdapterPosition) {
        this.view = view;
        this.items = items;
        this.itemAdapterPosition = itemAdapterPosition;
    }

    //INTERFACE IMPLEMENTATION: Icore2ui.Events
    @Override
    public void onActionComplete(Folder data) {
        items.remove(itemAdapterPosition);
        view.onMessage(StartPoint.getActivity().getString(R.string.result_themedeleted));
        view.onListUpdate();
    }
    @Override
    public void onError() {
        view.onMessage(StartPoint.getActivity().getString(R.string.result_error));
    }
}