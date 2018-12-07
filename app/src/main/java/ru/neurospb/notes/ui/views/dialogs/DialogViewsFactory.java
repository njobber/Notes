package ru.neurospb.notes.ui.views.dialogs;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;

import ru.neurospb.notes.R;
import ru.neurospb.notes.core.gateways.Icore2ui;
import ru.neurospb.notes.core.models.Folder;
import ru.neurospb.notes.ui.models.Theme;
import ru.neurospb.notes.ui.views.StartPoint;
import ru.neurospb.notes.ui.views.base.IbaseView;

/**
 * FACTORY DESIGN PATTERN
 * This factory creates DialogViews for certain actions.
 *
 * @author Alexey Utovka
 * @version alpha1
 * @since alpha1
 */

public class DialogViewsFactory {
    //DIALOG TYPES
    public static final int DIALOG_CREATE_NEW_THEME = 10;
    public static final int DIALOG_CONFIRM_DELETE = 20;
    public static final int DIALOG_RENAME_THEME = 30;

    /**
     * FACTORY METHOD
     * @param dialogType An int value of dialog's type.
     * @param theme A model to be processed
     * @param userOutputHandler A handler to process output for user
     * @param coreEventsHandler A handler to receive callbacks from CORE
     * @return A PRODUCT extended from AlertDialog
     */
    @NonNull
    public static AlertDialog create(
            int dialogType,
            Theme theme,
            IbaseView userOutputHandler,
            Icore2ui.Events<Folder> coreEventsHandler
    ) {
        switch (dialogType){
            default:
                return new DefaultDialog();
            case DIALOG_CREATE_NEW_THEME:
                return new CreateNewThemeDialog(theme, userOutputHandler, coreEventsHandler);
            case DIALOG_CONFIRM_DELETE:
                return new DeleteDialog(theme, coreEventsHandler);
            case DIALOG_RENAME_THEME:
                return new RenameThemeDialog(theme, userOutputHandler, coreEventsHandler);
        }
    }
    //FACTORY PRODUCT
    //DEFAULT
    //Simple error dialog without message and any actions
    private static class DefaultDialog extends AlertDialog {
        DefaultDialog() {
            super(StartPoint.getActivity());
            setTitle(StartPoint.getActivity().getString(R.string.title_error));
            setButton(BUTTON_POSITIVE,
                    StartPoint.getActivity().getString(R.string.ok),
                    new DLcancelNoAction()
            );
        }
    }
    //FACTORY PRODUCT
    //CREATE THEME
    //Asks user for name of new theme, checks user input, requests Core to make new theme
    //Actions: confirm, cancel
    private static class CreateNewThemeDialog extends AlertDialog {
        @SuppressLint("InflateParams")
        CreateNewThemeDialog(
                Theme parentTheme,
                IbaseView userOutputHandler,
                Icore2ui.Events<Folder> coreEventsHandler
        ) {
            super(StartPoint.getActivity());
            @SuppressWarnings("ConstantConditions")
            View customView = ((LayoutInflater) StartPoint.getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE))
                    .inflate(R.layout.dialog_input_themename, null);

            setTitle(StartPoint.getActivity().getString(R.string.title_newfolder));
            setView(customView);
            setButton(BUTTON_POSITIVE,
                    StartPoint.getActivity().getString(R.string.ok),
                    new DLconfirmNewTheme(customView, parentTheme, userOutputHandler, coreEventsHandler)
            );
            setButton(BUTTON_NEGATIVE,
                    StartPoint.getActivity().getString(R.string.cancel),
                    new DLcancelNoAction());
        }
    }
    //FACTORY PRODUCT
    //RENAME THEME
    //Asks user for new name of theme, checks user input, requests Core to update given theme
    //Actions: confirm, cancel
    private static class RenameThemeDialog extends AlertDialog {
        @SuppressLint("InflateParams")
        RenameThemeDialog(
                Theme theme2rename,
                IbaseView userOutputHandler,
                Icore2ui.Events<Folder> coreEventsHandler
        ) {
            super(StartPoint.getActivity());
            @SuppressWarnings("ConstantConditions")
            View customView = ((LayoutInflater) StartPoint.getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE))
                    .inflate(R.layout.dialog_input_themename, null);

            setTitle(StartPoint.getActivity().getString(R.string.title_rename));
            setView(customView);
            setButton(BUTTON_POSITIVE,
                    StartPoint.getActivity().getString(R.string.ok),
                    new DLconfirmRenameTheme(theme2rename, customView, userOutputHandler, coreEventsHandler)
            );
            setButton(BUTTON_NEGATIVE,
                    StartPoint.getActivity().getString(R.string.cancel),
                    new DLcancelNoAction());
        }
    }
    //FACTORY PRODUCT
    //DELETE THEME
    //Asks user for confirmation to delete given theme, requests Core to delete given theme
    //Actions: confirm, cancel
    private static class DeleteDialog extends AlertDialog {
        DeleteDialog(
                Theme theme2delete,
                Icore2ui.Events<Folder> coreEventsHandler
        ) {
            super(StartPoint.getActivity());
            setTitle(StartPoint.getActivity().getString(R.string.title_delete));
            setMessage(StartPoint.getActivity().getString(R.string.dialog_confirmation_delete));
            setButton(BUTTON_POSITIVE,
                    StartPoint.getActivity().getString(R.string.ok),
                    new DLconfirmDeleteTheme(coreEventsHandler, theme2delete));
            setButton(BUTTON_NEGATIVE,
                    StartPoint.getActivity().getString(R.string.cancel),
                    new DLcancelNoAction());
        }
    }
}