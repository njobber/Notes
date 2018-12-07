package ru.neurospb.notes.ui.views.themelist.recyclerview.selection;

import android.support.annotation.NonNull;

import androidx.recyclerview.selection.SelectionTracker;

/**
 * An implementation of SelectionTracker.SelectionPredicate for ThemeList.
 *
 * @author Alexey Utovka
 * @version alpha1
 * @since alpha1
 */

public class ThemeSelectionPredicate extends SelectionTracker.SelectionPredicate<Long> {

    //IMPLEMENTATION: SelectionTracker.SelectionPredicate
    @Override
    public boolean canSetStateForKey(@NonNull Long aLong, boolean b) {
        return true;
    }
    @Override
    public boolean canSetStateAtPosition(int i, boolean b) {
        return true;
    }
    @Override
    public boolean canSelectMultiple() {
        return false;
    }
}