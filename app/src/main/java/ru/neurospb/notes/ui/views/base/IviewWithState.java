package ru.neurospb.notes.ui.views.base;

import android.os.Bundle;

/**
 * An interface for view with state.
 *
 * @author Alexey Utovka
 * @version alpha1
 * @since alpha1
 */

public interface IviewWithState {
    /**
     * Activity's application state support
     */
    void saveState(Bundle state);
    void loadState(Bundle state);
}