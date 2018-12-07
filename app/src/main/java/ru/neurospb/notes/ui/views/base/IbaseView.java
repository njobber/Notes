package ru.neurospb.notes.ui.views.base;

/**
 * An interface for base view.
 *
 * @author Alexey Utovka
 * @version alpha1
 * @since alpha1
 */

public interface IbaseView {
    /**
     * Shows a message to user.
     * @param message A message.
     */
    void onMessage(String message);
}