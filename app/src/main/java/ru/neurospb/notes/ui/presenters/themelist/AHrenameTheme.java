package ru.neurospb.notes.ui.presenters.themelist;

import java.util.List;

import ru.neurospb.notes.R;
import ru.neurospb.notes.core.gateways.Icore2ui;
import ru.neurospb.notes.core.models.Folder;
import ru.neurospb.notes.ui.models.utilities.UIthemeUnpacker;
import ru.neurospb.notes.ui.views.StartPoint;
import ru.neurospb.notes.ui.views.themelist.recyclerview.IThemeListItem;
import ru.neurospb.notes.ui.views.themelist.recyclerview.ThemeItemView;

/**
 * Handler for action: rename selected theme.
 *
 * @author Alexey Utovka
 * @version alpha1
 * @since alpha1
 */

public class AHrenameTheme implements Icore2ui.Events<Folder> {
    private IThemeList.View view;
    private List<IThemeListItem> items;
    private int itemAdapterPosition;

    //CONSTRUCTOR
    AHrenameTheme(IThemeList.View view, List<IThemeListItem> items, int itemAdapterPosition) {
        this.view = view;
        this.items = items;
        this.itemAdapterPosition = itemAdapterPosition;
    }

    //INTERFACE IMPLEMENTATION: Icore2ui.Events
    @Override
    public void onActionComplete(Folder data) {

        ThemeItemView themeItem = new ThemeItemView(
                new UIthemeUnpacker().pack(data),
                items.get(itemAdapterPosition).getItemId()
        );
        items.set(itemAdapterPosition, themeItem);
        view.onMessage(StartPoint.getActivity().getString(R.string.result_themerenamed));
        view.onListUpdate();
    }
    @Override
    public void onError() {
        view.onMessage(StartPoint.getActivity().getString(R.string.result_error));
    }
}