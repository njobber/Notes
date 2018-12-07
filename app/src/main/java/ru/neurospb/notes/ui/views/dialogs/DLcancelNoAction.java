package ru.neurospb.notes.ui.views.dialogs;

import android.content.DialogInterface;

/**
 * Dialog listener for cancel without action.
 *
 * @author Alexey Utovka
 * @version alpha1
 * @since alpha1
 */

class DLcancelNoAction implements DialogInterface.OnClickListener {

    //INTERFACE IMPLEMENTATION: DialogInterface.OnClickListener
    @Override
    public void onClick(DialogInterface dialog, int i) {
        dialog.dismiss();
    }
}