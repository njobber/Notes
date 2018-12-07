package ru.neurospb.notes.ui.presenters.themelist;

import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;

import ru.neurospb.notes.R;
import ru.neurospb.notes.core.gateways.Icore2ui;
import ru.neurospb.notes.core.models.Folder;
import ru.neurospb.notes.ui.models.utilities.UIthemeUnpacker;
import ru.neurospb.notes.ui.views.StartPoint;

import static ru.neurospb.notes.ui.presenters.base.Ireceiver.BROADCAST_UI;
import static ru.neurospb.notes.ui.presenters.base.Ireceiver.PARAM_DATA;
import static ru.neurospb.notes.ui.presenters.base.Ireceiver.PARAM_TYPE;
import static ru.neurospb.notes.ui.presenters.base.Ireceiver.TYPE_NAVIGATE_TO;

/**
 * Handler for action: create new theme.
 *
 * @author Alexey Utovka
 * @version alpha1
 * @since alpha1
 */

public class AHnewTheme implements Icore2ui.Events<Folder> {
    private IThemeList.View view;

    //CONSTRUCTOR
    AHnewTheme(IThemeList.View view) {
        this.view = view;
    }

    //INTERFACE IMPLEMENTATION: Icore2ui.Events
    @Override
    public void onActionComplete(Folder createdFolder) {
        Intent intent = new Intent(BROADCAST_UI);
        intent.putExtra(PARAM_TYPE, TYPE_NAVIGATE_TO);
        intent.putExtra(PARAM_DATA, new UIthemeUnpacker().pack(createdFolder));
        LocalBroadcastManager.getInstance(StartPoint.getActivity()).sendBroadcast(intent);
    }
    @Override
    public void onError() {
        view.onMessage(StartPoint.getActivity().getString(R.string.result_duplicatethemename));
    }
}