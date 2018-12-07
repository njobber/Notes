package ru.neurospb.notes.db.controllers;

import ru.neurospb.notes.db.models.IdbModel;

/**
 * An interface for basic operations on DB layer's model.
 *
 * @author Alexey Utovka
 * @version alpha1
 * @since alpha1
 */

public interface IbasicModelOperator {
    /**
     * Creates a new record of given model's data in Data Base
     * @param model A data.
     * @return TRUE - data saved as new,
     *         FALSE - data failed to save (data has duplicate in data base)
     */
    boolean put(IdbModel model);
    /**
     * Updates a record of given model's data in Data Base
     * @param model A data.
     * @return TRUE - data updated,
     *         FALSE - data failed to update
     */
    boolean post(IdbModel model);
    /**
     * Deletes a record of given model's data in Data Base
     * @param model A data.
     * @return TRUE - data deleted,
     *         FALSE - data failed to delete
     */
    boolean delete(IdbModel model);
}