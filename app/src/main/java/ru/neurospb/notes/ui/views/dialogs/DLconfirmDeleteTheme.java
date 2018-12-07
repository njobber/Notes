package ru.neurospb.notes.ui.views.dialogs;

import android.content.DialogInterface;

import ru.neurospb.notes.core.gateways.Icore2ui;
import ru.neurospb.notes.core.models.Folder;
import ru.neurospb.notes.core.usecases.DeleteFolder;
import ru.neurospb.notes.db.controllers.ThemeController;
import ru.neurospb.notes.ui.models.Theme;
import ru.neurospb.notes.ui.models.utilities.UIthemePacker;

/**
 * Dialog listener for confirmation to delete selected theme.
 *
 * @author Alexey Utovka
 * @version alpha1
 * @since alpha1
 */

public class DLconfirmDeleteTheme implements DialogInterface.OnClickListener {
    private Icore2ui.Events<Folder> coreEventsHandler;
    private Theme theme2delete;

    //CONSTRUCTOR
    DLconfirmDeleteTheme(Icore2ui.Events<Folder> coreEventsHandler, Theme theme2delete) {
        this.coreEventsHandler = coreEventsHandler;
        this.theme2delete = theme2delete;
    }

    //INTERFACE IMPLEMENTATION: DialogInterface.OnClickListener
    @Override
    public void onClick(DialogInterface dialog, int which) {
        DeleteFolder deleteFolderUseCase = new DeleteFolder(
                new UIthemePacker().pack(theme2delete),
                new ThemeController(),
                coreEventsHandler
        );
        deleteFolderUseCase.execute();
        dialog.dismiss();
    }
}