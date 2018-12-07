package ru.neurospb.notes.ui.views.dialogs;

import android.content.DialogInterface;
import android.view.View;
import android.widget.EditText;

import ru.neurospb.notes.R;
import ru.neurospb.notes.core.gateways.Icore2ui;
import ru.neurospb.notes.core.models.Folder;
import ru.neurospb.notes.core.usecases.CreateFolder;
import ru.neurospb.notes.db.controllers.ThemeController;
import ru.neurospb.notes.ui.models.Theme;
import ru.neurospb.notes.ui.models.utilities.UIthemePacker;
import ru.neurospb.notes.ui.models.utilities.UIutilityTheme;
import ru.neurospb.notes.ui.views.base.IbaseView;

/**
 * Dialog listener for confirmation to create new theme.
 *
 * @author Alexey Utovka
 * @version alpha1
 * @since alpha1
 */

class DLconfirmNewTheme implements DialogInterface.OnClickListener {
    private EditText UIview;
    private IbaseView userOutputHandler;
    private Icore2ui.Events<Folder> coreEventsHandler;
    private Theme parentTheme;

    //CONSTRUCTOR
    DLconfirmNewTheme(View customDialogView,
                      Theme parentTheme,
                      IbaseView userOutputHandler,
                      Icore2ui.Events<Folder> coreEventsHandler) {
        UIview = customDialogView.findViewById(R.id.input_theme_name);
        this.userOutputHandler = userOutputHandler;
        this.coreEventsHandler = coreEventsHandler;
        this.parentTheme = parentTheme;
    }

    //INTERFACE IMPLEMENTATION: DialogInterface.OnClickListener
    @Override
    public void onClick(DialogInterface dialog, int i) {
        if (UIutilityInput.validateUserInput(UIview, userOutputHandler)) {
            Theme newTheme = new Theme();
            newTheme.setName(UIutilityInput.getUserInput(UIview));
            newTheme.setPath(UIutilityTheme.getChildPath(parentTheme));
            CreateFolder createFolderUseCase = new CreateFolder(
                    new UIthemePacker().pack(newTheme),
                    new ThemeController(),
                    coreEventsHandler
            );
            createFolderUseCase.execute();
        }
        dialog.dismiss();
    }
}