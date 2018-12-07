package ru.neurospb.notes.ui.presenters.base;

import android.os.Bundle;

/**
 * An interface for base presenter.
 *
 * @author Alexey Utovka
 * @version alpha1
 * @since alpha1
 */

public interface IbasePresenter {
    /**
     * Activity's lifecycle support
     */
    void resume();
    void pause();
    /**
     * Activity's application state support
     */
    void saveState(Bundle state);
    void loadState(Bundle state);
    /**
     * Sets presenter's state
     * @param newState A Bundle with presenter's state to be set up.
     */
    void setState(Bundle newState);
}