package ru.neurospb.notes.ui.presenters.base;

/**
 * Base interface for UI broadcast receiver.
 *
 * @author Alexey Utovka
 * @version alpha1
 * @since alpha1
 */

public interface Ireceiver {
    String BROADCAST_UI = "ru.neurospb.notes.broadcast.ui";

    String PARAM_TYPE = "ru.neurospb.notes.broadcast.eventType";
    String PARAM_DATA = "ru.neurospb.notes.broadcast.eventData";

    int TYPE_NAVIGATE_TO = 150;
    int TYPE_THEME_RENAME = 151;
    int TYPE_THEME_DELETE = 152;
    int TYPE_CLEAR_ITEM_SELECTION = 160;
}