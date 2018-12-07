package ru.neurospb.notes.ui.views.dialogs;

import android.widget.EditText;

import ru.neurospb.notes.R;
import ru.neurospb.notes.ui.views.StartPoint;
import ru.neurospb.notes.ui.views.base.IbaseView;

import static ru.neurospb.notes.core.models.Folder.PATH_SPLITTER;

/**
 * Supplies utility methods for user input.
 *
 * @author Alexey Utovka
 * @version alpha1
 * @since alpha1
 */

class UIutilityInput {
    //CLASS METHODS
    /**
     * Gets a value of user input.
     * @param UIview An EditText with user input.
     * @return A String of user input's value.
     */
    static String getUserInput(EditText UIview) {
        return UIview.getText().toString().trim();
    }
    /**
     * Makes a common checks of user input
     * @param UIview An EditText with user input.
     * @param userOutputHandler A handler to process output for user
     * @return TRUE - user input validated
     *         FALSE - user input is invalid
     */
    static boolean validateUserInput(EditText UIview, IbaseView userOutputHandler) {
        String UItext = getUserInput(UIview);
        if (UItext.length() > 0) {
            if (UItext.indexOf(PATH_SPLITTER) != -1) {
                userOutputHandler.onMessage(StartPoint.getActivity().getString(R.string.dialog_message_wronginput));
                return false;
            } else
                return true;
        } else {
            userOutputHandler.onMessage(StartPoint.getActivity().getString(R.string.dialog_message_emptyinput));
            return false;
        }
    }
}