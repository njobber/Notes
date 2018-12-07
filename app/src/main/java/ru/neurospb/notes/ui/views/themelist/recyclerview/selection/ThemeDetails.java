package ru.neurospb.notes.ui.views.themelist.recyclerview.selection;

import android.support.annotation.Nullable;

import androidx.recyclerview.selection.ItemDetailsLookup;

/**
 * An implementation of ItemDetailsLookup.ItemDetails for ThemeItem.
 *
 * @author Alexey Utovka
 * @version alpha1
 * @since alpha1
 */

public class ThemeDetails extends ItemDetailsLookup.ItemDetails<Long> {
    private int adapterPosition;
    private Long itemKey;

    //CONSTRUCTOR
    public ThemeDetails(int adapterPosition, Long itemKey) {
        this.adapterPosition = adapterPosition;
        this.itemKey = itemKey;
    }

    //IMPLEMENTATION: ItemDetailsLookup.ItemDetails
    @Override
    public int getPosition() {
        return adapterPosition;
    }
    @Nullable
    @Override
    public Long getSelectionKey() {
        return itemKey;
    }
}