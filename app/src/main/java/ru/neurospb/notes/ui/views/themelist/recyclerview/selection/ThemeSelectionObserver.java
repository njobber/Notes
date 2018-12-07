package ru.neurospb.notes.ui.views.themelist.recyclerview.selection;

import android.support.v7.view.ActionMode;

import androidx.recyclerview.selection.SelectionTracker;
import ru.neurospb.notes.ui.views.toolbar.ThemeActionsController;
import ru.neurospb.notes.ui.views.StartPoint;

/**
 * An implementation of SelectionTracker.SelectionObserver for ThemeList.
 *
 * @author Alexey Utovka
 * @version alpha1
 * @since alpha1
 */

public class ThemeSelectionObserver extends SelectionTracker.SelectionObserver<Long> {
    private SelectionTracker<Long> selectionTracker;
    private ActionMode themeActionMode;

    //CONSTRUCTOR
    public ThemeSelectionObserver(SelectionTracker<Long> selectionTracker, ActionMode themeActionMode) {
        this.selectionTracker = selectionTracker;
        this.themeActionMode = themeActionMode;
    }

    //IMPLEMENTATION: SelectionTracker.SelectionObserver
    @Override
    public void onSelectionChanged() {
        super.onSelectionChanged();
        if (selectionTracker.hasSelection() && themeActionMode == null)
            themeActionMode = StartPoint.getActivity().startSupportActionMode(new ThemeActionsController());
        else if (!selectionTracker.hasSelection()) {
            if (themeActionMode == null) return;
            themeActionMode.finish();
            themeActionMode = null;
        }
    }
}