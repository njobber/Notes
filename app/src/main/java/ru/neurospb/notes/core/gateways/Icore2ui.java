package ru.neurospb.notes.core.gateways;

import android.support.annotation.NonNull;

/**
 * An interface for UI layer.
 *
 * @author Alexey Utovka
 * @version alpha1
 * @since alpha1
 */

public interface Icore2ui {
    interface Data<T> {
        /**
         * This UI's callback fired when data loaded and ready.
         * @param data Some data ready to use by UI layer.
         */
        void onDataLoad(@NonNull T data);
    }
    interface Events<T> {
        /**
         * This UI's callback fired when some action completed.
         * @param data Some data to use by UI layer OR null.
         */
        void onActionComplete(T data);
        /**
         * This UI's callback fired when some error happened.
         */
        void onError();
    }
}