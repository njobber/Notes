package ru.neurospb.notes.ui.views.themelist.recyclerview.selection;

import androidx.recyclerview.selection.ItemDetailsLookup;

/**
 * An interface for ViewHolder with support of ItemDetailsLookup.ItemDetails
 *
 * @author Alexey Utovka
 * @version alpha1
 * @since alpha1
 */

public interface IviewHolderWithDetails<T> {
    /**
     * Gets an implementation of ItemDetailsLookup.ItemDetails for given instance of item's ViewHolder
     * @return An implementation of ItemDetailsLookup.ItemDetails
     */
    ItemDetailsLookup.ItemDetails<T> getItemDetails();
    /**
     * Turns on/off activation state of ViewHolder's Views
     * @param isOn TRUE - turns on activation state
     *             FALSE - turns off activation state
     */
    void setActivated(boolean isOn);
}