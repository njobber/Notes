package ru.neurospb.notes.ui.views.themelist.recyclerview;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.selection.ItemDetailsLookup;
import ru.neurospb.notes.R;
import ru.neurospb.notes.ui.views.StartPoint;
import ru.neurospb.notes.ui.views.themelist.recyclerview.selection.IviewHolderWithDetails;
import ru.neurospb.notes.ui.views.themelist.recyclerview.selection.ThemeDetails;

import static ru.neurospb.notes.ui.views.themelist.recyclerview.IThemeListItem.TYPE_THEME;

/**
 * FACTORY DESIGN PATTERN
 * This factory creates ViewHolders for certain item types.
 *
 * @author Alexey Utovka
 * @version alpha1
 * @since alpha1
 */

public class ThemeListViewHoldersFactory {
    /**
     * FACTORY METHOD
     * Used in RecyclerView.Adapter.onCreateViewHolder()
     * @param parent First parameter of RecyclerView.Adapter.onCreateViewHolder()
     * @param viewType Second parameter of RecyclerView.Adapter.onCreateViewHolder()
     * @return A PRODUCT extended from RecyclerView.ViewHolder
     */
    @NonNull
    static RecyclerView.ViewHolder create(ViewGroup parent, int viewType) {
        switch (viewType) {
            default:
            case TYPE_THEME:
                View themeView = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_theme, parent, false);
                return new ThemeListViewHoldersFactory.ThemeViewHolder(themeView);
        }
    }
    //FACTORY PRODUCT
    //Theme
    public static class ThemeViewHolder extends RecyclerView.ViewHolder implements IviewHolderWithDetails<Long> {
        CardView themeWrapper;
        TextView themeName;

        ThemeViewHolder(View itemView) {
            super(itemView);
            themeWrapper = itemView.findViewById(R.id.themeitem_wrapper);
            themeName = itemView.findViewById(R.id.themeitem_name);
        }

        //INTERFACE IMPLEMENTATION: IviewHolderWithDetails
        @Override
        public ItemDetailsLookup.ItemDetails<Long> getItemDetails() {
            return new ThemeDetails(getAdapterPosition(), getItemId());
        }
        @Override
        public void setActivated(boolean activated) {
            themeWrapper.setActivated(activated);
            if (activated)
                themeWrapper.setCardBackgroundColor(StartPoint.getActivity().getResources().getColor(R.color.colorPrimaryDark));
            else
                themeWrapper.setCardBackgroundColor(StartPoint.getActivity().getResources().getColor(R.color.colorPrimaryLight));
        }
    }
}