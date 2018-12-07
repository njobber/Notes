package ru.neurospb.notes.db.controllers;

import java.util.List;

import ru.neurospb.notes.db.models.IdbModel;
import ru.neurospb.notes.db.models.Theme;

/**
 * An interface for operations on Theme's content.
 *
 * @author Alexey Utovka
 * @version alpha1
 * @since alpha1
 */

public interface IthemeContentOperator {
    /**
     * Gets all records from Data Base with given parent
     * @param parentTheme A parent to be searched for.
     * @return A list of records (DB layer's models) with given parent.
     */
    List<IdbModel> getChildrenList(Theme parentTheme);
}