package ru.neurospb.notes.ui.views.themelist.recyclerview;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.List;

import androidx.recyclerview.selection.SelectionTracker;

/**
 * Adapter for ThemeList's recyclerview.
 * Used to show theme's content (subthemes) in ThemeList view.
 *
 * @author Alexey Utovka
 * @version alpha1
 * @since alpha1
 */

public class ThemeListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<IThemeListItem> items;
    private SelectionTracker<Long> selectionTracker;

    //CONSTRUCTOR
    public ThemeListAdapter(List<IThemeListItem> items) {
        this.items = items;
        setHasStableIds(true);
    }

    //IMPLEMENTATION: RecyclerView.Adapter
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return ThemeListViewHoldersFactory.create(parent, viewType);
    }
    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, int position) {
        items.get(position).onBindViewHolder(holder, selectionTracker);
    }
    @Override
    public int getItemCount() {
        if (items == null) return 0;
        return items.size();
    }
    @Override
    public int getItemViewType(int position) {
        return items.get(position).getItemViewType();
    }
    @Override
    public long getItemId(int position) {
        return items.get(position).getItemId();
    }

    //SETTERS/GETTERS
    public void setSelectionTracker(SelectionTracker<Long> selectionTracker) {
        this.selectionTracker = selectionTracker;
    }
    public IThemeListItem getItem(int position) {
        return items.get(position);
    }
}