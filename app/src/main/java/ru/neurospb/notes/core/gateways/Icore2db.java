package ru.neurospb.notes.core.gateways;

import ru.neurospb.notes.core.models.Folder;

/**
 * An interface for DB layer.
 *
 * @author Alexey Utovka
 * @version alpha1
 * @since alpha1
 */

public interface Icore2db {
    interface Entity<E> {
        /**
         * Creates an Entity in Data Base
         * @param data An entity.
         * @return TRUE - data saved as new,
         *         FALSE - data failed to save (data has duplicate in data base)
         */
        boolean create(E data);
        /**
         * Updates an Entity in Data Base
         * @param oldVersionOfData An entity to be updated. It is used for identification purposes.
         * @param newVersionOfData An updated entity.
         * @return TRUE - data updated,
         *         FALSE - data failed to update
         */
        boolean update(E oldVersionOfData, E newVersionOfData);
        /**
         * Deletes an Entity from Data Base
         * @param data An entity.
         * @return TRUE - data deleted,
         *         FALSE - data failed to delete
         */
        boolean delete(E data);
    }

    interface Listing<T> {
        /**
         * Returns list of specified items(folders) for given parent(folder).
         * @param parent A parent Folder.
         * @return Specified type of content (in the case(alpha1) >> a list of folders OR empty list)
         */
        T get(Folder parent);
    }
}