package ru.neurospb.notes.ui.views.dialogs;

import android.content.DialogInterface;
import android.view.View;
import android.widget.EditText;

import ru.neurospb.notes.R;
import ru.neurospb.notes.core.gateways.Icore2ui;
import ru.neurospb.notes.core.models.Folder;
import ru.neurospb.notes.core.usecases.RenameFolder;
import ru.neurospb.notes.db.controllers.ThemeController;
import ru.neurospb.notes.ui.models.Theme;
import ru.neurospb.notes.ui.models.utilities.UIthemePacker;
import ru.neurospb.notes.ui.views.base.IbaseView;

/**
 * Dialog listener for confirmation to rename selected theme.
 *
 * @author Alexey Utovka
 * @version alpha1
 * @since alpha1
 */

public class DLconfirmRenameTheme implements DialogInterface.OnClickListener {
    private EditText UIview;
    private IbaseView userOutputHandler;
    private Icore2ui.Events<Folder> coreEventsHandler;
    private Theme theme2rename;

    //CONSTRUCTOR
    DLconfirmRenameTheme(Theme theme2rename,
                                View customDialogView,
                                IbaseView userOutputHandler,
                                Icore2ui.Events<Folder> coreEventsHandler) {
        UIview = customDialogView.findViewById(R.id.input_theme_name);
        this.userOutputHandler = userOutputHandler;
        this.coreEventsHandler = coreEventsHandler;
        this.theme2rename = theme2rename;
    }

    //INTERFACE IMPLEMENTATION: DialogInterface.OnClickListener
    @Override
    public void onClick(DialogInterface dialog, int i) {
        if (UIutilityInput.validateUserInput(UIview, userOutputHandler)) {
            RenameFolder renameFolderUseCase = new RenameFolder(
                    new UIthemePacker().pack(theme2rename),
                    UIutilityInput.getUserInput(UIview),
                    new ThemeController(),
                    coreEventsHandler
            );
            renameFolderUseCase.execute();
        }
        dialog.dismiss();
    }
}