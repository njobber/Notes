package ru.neurospb.notes.core.usecases.base;

/**
 * The basic interface for each UseCase.
 *
 * @author Alexey Utovka
 * @version alpha1
 * @since alpha1
 */

interface IUseCase {
    /**
     * The main method of UseCase. It'll ensure that each UseCase'd be executed in a background
     * thread.
     */
    void execute();
    /**
     * This method contains the actual UseCase's logic.
     * It SHOULD NOT BE USED DIRECTLY, USE execute() method to make sure the UseCase's
     * logic is done on a background thread.
     */
    void run();
    /**
     * Callback. It'll be executed after .run().
     */
    void onFinished();
}