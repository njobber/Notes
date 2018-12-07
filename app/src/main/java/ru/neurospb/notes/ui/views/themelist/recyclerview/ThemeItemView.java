package ru.neurospb.notes.ui.views.themelist.recyclerview;

import android.support.v7.widget.RecyclerView;

import java.io.Serializable;

import androidx.recyclerview.selection.SelectionTracker;
import ru.neurospb.notes.ui.models.Theme;

/**
 * A View for ThemeList's item of Theme type.
 *
 * @author Alexey Utovka
 * @version alpha1
 * @since alpha1
 */

public class ThemeItemView implements IThemeListItem {
    private Theme theme;
    private long id;

    //CONSTRUCTOR
    public ThemeItemView(Theme theme, long id) {
        this.theme = theme;
        this.id = id;
    }

    //IMPLEMENTATION:IThemeListItem
    @Override
    public int getItemViewType() {
        return TYPE_THEME;
    }
    @Override
    public long getItemId() {
        return id;
    }
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, SelectionTracker<Long> selectionTracker) {
        ThemeListViewHoldersFactory.ThemeViewHolder themeViewHolder = (ThemeListViewHoldersFactory.ThemeViewHolder) viewHolder;
        themeViewHolder.themeName.setText(theme.getName());
        if (selectionTracker == null) return;
        themeViewHolder.setActivated(selectionTracker.isSelected((themeViewHolder.getItemDetails().getSelectionKey())));
    }
    @Override
    public Serializable getItemData() {
        return theme;
    }
}