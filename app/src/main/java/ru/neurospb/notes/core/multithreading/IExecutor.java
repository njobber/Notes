package ru.neurospb.notes.core.multithreading;

import ru.neurospb.notes.core.usecases.base.AbstractUseCase;

/**
 * A basic interface for Executor.
 *
 * @author Alexey Utovka
 * @version alpha1
 * @since alpha1
 */

public interface IExecutor {
    /**
     * Calls UseCase's .run() method on a background thread.
     * @param abstractUseCase An AbstractUseCase to run.
     */
    void execute(AbstractUseCase abstractUseCase);

    interface UIthread {
        /**
         * Makes runnable operation run in the UI thread.
         * @param runnable A Runnable to execute in the UI thread.
         */
        void post(final Runnable runnable);
    }
}