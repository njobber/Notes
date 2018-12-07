package ru.neurospb.notes.ui.views.themelist.recyclerview;

import android.support.v7.widget.RecyclerView;

import java.io.Serializable;

import androidx.recyclerview.selection.SelectionTracker;

/**
 * An interface for item of Theme List.
 *
 * @author Alexey Utovka
 * @version alpha1
 * @since alpha1
 */

public interface IThemeListItem {
    //CONSTANTS
    int TYPE_THEME = 1;

    /**
     * Used in RecyclerView.Adapter.getItemViewType()
     * @return A type of item (see CONSTANTS section)
     */
    int getItemViewType();
    /**
     * Used in RecyclerView.Adapter.getItemId()
     * @return An unique id of item.
     */
    long getItemId();
    /**
     * Used in RecyclerView.Adapter.onBindViewHolder()
     * @param viewHolder First parameter of RecyclerView.Adapter.onBindViewHolder()
     */
    void onBindViewHolder(RecyclerView.ViewHolder viewHolder, SelectionTracker<Long> selectionTracker);
    /**
     * Gets an item's model
     * @return An item's model
     */
    Serializable getItemData();
}