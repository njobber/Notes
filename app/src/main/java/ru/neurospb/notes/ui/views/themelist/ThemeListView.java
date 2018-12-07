package ru.neurospb.notes.ui.views.themelist;

import android.os.Bundle;
import android.support.v7.view.ActionMode;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import androidx.recyclerview.selection.SelectionTracker;
import androidx.recyclerview.selection.StableIdKeyProvider;
import androidx.recyclerview.selection.StorageStrategy;
import ru.neurospb.notes.R;
import ru.neurospb.notes.ui.presenters.themelist.IThemeList;
import ru.neurospb.notes.ui.views.StartPoint;
import ru.neurospb.notes.ui.views.base.AbstractView;
import ru.neurospb.notes.ui.views.themelist.recyclerview.selection.OnItemListener;
import ru.neurospb.notes.ui.views.themelist.recyclerview.IThemeListItem;
import ru.neurospb.notes.ui.views.themelist.recyclerview.selection.ThemeDetailsLookup;
import ru.neurospb.notes.ui.views.themelist.recyclerview.ThemeListAdapter;
import ru.neurospb.notes.ui.views.themelist.recyclerview.selection.ThemeSelectionObserver;
import ru.neurospb.notes.ui.views.themelist.recyclerview.selection.ThemeSelectionPredicate;

/**
 * Shows theme's content (list of subthemes).
 *
 * @author Alexey Utovka
 * @version alpha1
 * @since alpha1
 */

public class ThemeListView extends AbstractView implements IThemeList.View {
    private RecyclerView recyclerView;
    private SelectionTracker<Long> selectionTracker;
    @SuppressWarnings("FieldCanBeLocal")
    private ActionMode themeActionMode;
    //CONSTANTS
    private static final String THEME_SELECTION_ID = "theme_selection";

    //CONSTRUCTOR
    public ThemeListView(List<IThemeListItem> items) {
        super(R.layout.theme);
        renderSelf();
        themeActionMode = null;
        recyclerView = StartPoint.getActivity().findViewById(R.id.theme_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(StartPoint.getActivity()));
        ThemeListAdapter adapter = new ThemeListAdapter(items);
        recyclerView.setAdapter(adapter);
        selectionTracker = new SelectionTracker.Builder<>(
                THEME_SELECTION_ID,
                recyclerView,
                new StableIdKeyProvider(recyclerView),
                new ThemeDetailsLookup(recyclerView),
                StorageStrategy.createLongStorage()
        ).withSelectionPredicate(new ThemeSelectionPredicate())
         .withOnItemActivatedListener(new OnItemListener(recyclerView))
         .build();
        selectionTracker.addObserver(new ThemeSelectionObserver(selectionTracker, themeActionMode));
        adapter.setSelectionTracker(selectionTracker);
    }

    //INTERFACE IMPLEMENTATION: IThemeList.View
    @Override
    @SuppressWarnings("ConstantConditions")
    public void onListUpdate() {
        recyclerView.getAdapter().notifyDataSetChanged();
    }
    @Override
    public void clearSelection() {
        selectionTracker.clearSelection();
    }
    @Override
    public int getSelectedAdapterPosition() {
        return recyclerView
                .findViewHolderForItemId(selectionTracker.getSelection().iterator().next())
                .getAdapterPosition();
    }
    @Override
    public IThemeListItem getSelectedItem() {
        int position = getSelectedAdapterPosition();
        if (recyclerView.getAdapter() instanceof ThemeListAdapter)
            return ((ThemeListAdapter)recyclerView.getAdapter()).getItem(position);
        else return null;
    }

    //INTERFACE IMPLEMENTATION: IviewWithState
    @Override
    public void saveState(Bundle state) {
        selectionTracker.onSaveInstanceState(state);
    }
    @Override
    public void loadState(Bundle state) {
        selectionTracker.onRestoreInstanceState(state);
    }
}