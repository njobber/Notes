package ru.neurospb.notes.ui.views.themelist.recyclerview.selection;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;

import androidx.recyclerview.selection.ItemDetailsLookup;
import ru.neurospb.notes.ui.views.themelist.recyclerview.ThemeListViewHoldersFactory;

/**
 * An implementation of ItemDetailsLookup for ThemeItem.
 *
 * @author Alexey Utovka
 * @version alpha1
 * @since alpha1
 */

public class ThemeDetailsLookup extends ItemDetailsLookup<Long> {
    private RecyclerView recyclerView;

    //CONSTRUCTOR
    public ThemeDetailsLookup(RecyclerView recyclerView) {
        this.recyclerView = recyclerView;
    }

    //IMPLEMENTATION: ItemDetailsLookup
    @Nullable
    @Override
    public ItemDetails<Long> getItemDetails(@NonNull MotionEvent motionEvent) {
        View view = recyclerView.findChildViewUnder(motionEvent.getX(), motionEvent.getY());
        if (view != null) {
            RecyclerView.ViewHolder viewHolder = recyclerView.getChildViewHolder(view);
            if (viewHolder instanceof ThemeListViewHoldersFactory.ThemeViewHolder) {
                return ((ThemeListViewHoldersFactory.ThemeViewHolder) viewHolder).getItemDetails();
            }
        }
        return null;
    }
}