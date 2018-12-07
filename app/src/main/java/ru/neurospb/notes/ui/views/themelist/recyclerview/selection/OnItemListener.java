package ru.neurospb.notes.ui.views.themelist.recyclerview.selection;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;

import androidx.recyclerview.selection.ItemDetailsLookup;
import androidx.recyclerview.selection.OnItemActivatedListener;
import ru.neurospb.notes.ui.views.StartPoint;
import ru.neurospb.notes.ui.views.themelist.recyclerview.IThemeListItem;
import ru.neurospb.notes.ui.views.themelist.recyclerview.ThemeItemView;
import ru.neurospb.notes.ui.views.themelist.recyclerview.ThemeListAdapter;

import static ru.neurospb.notes.ui.presenters.base.Ireceiver.BROADCAST_UI;
import static ru.neurospb.notes.ui.presenters.base.Ireceiver.PARAM_DATA;
import static ru.neurospb.notes.ui.presenters.base.Ireceiver.PARAM_TYPE;
import static ru.neurospb.notes.ui.presenters.base.Ireceiver.TYPE_NAVIGATE_TO;

/**
 * OnItemActivatedListener to open theme.
 *
 * @author Alexey Utovka
 * @version alpha1
 * @since alpha1
 */

public class OnItemListener implements OnItemActivatedListener<Long> {
    private RecyclerView recyclerView;

    //CONSTRUCTOR
    public OnItemListener(RecyclerView recyclerView) {
        this.recyclerView = recyclerView;
    }

    //INTERFACE IMPLEMENTATION: OnItemActivatedListener
    @Override
    public boolean onItemActivated(@NonNull ItemDetailsLookup.ItemDetails<Long> itemDetails, @NonNull MotionEvent motionEvent) {
        ThemeListAdapter recyclerViewAdapter = (ThemeListAdapter) recyclerView.getAdapter();
        if (recyclerViewAdapter == null) return false;
        IThemeListItem item = recyclerViewAdapter.getItem(itemDetails.getPosition());
        if (item instanceof ThemeItemView) {
            Intent intent = new Intent(BROADCAST_UI);
            intent.putExtra(PARAM_TYPE, TYPE_NAVIGATE_TO);
            intent.putExtra(PARAM_DATA, item.getItemData());
            LocalBroadcastManager.getInstance(StartPoint.getActivity()).sendBroadcast(intent);
        }
        return true;
    }
}