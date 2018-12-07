package ru.neurospb.notes.core.multithreading;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import ru.neurospb.notes.core.usecases.base.AbstractUseCase;

/**
 * A basic interface for Executor.
 *
 * @author Alexey Utovka
 * @version alpha1
 * @since alpha1
 */

public class Executor implements IExecutor {
    private static volatile Executor singletonExecutor; // This is a singleton
    private ThreadPoolExecutor threadPoolExecutor;
    //CONSTANTS
    private static final int CORE_POOL_SIZE = 3;
    private static final int MAX_POOL_SIZE = 5;
    private static final long KEEP_ALIVE_TIME = 120;
    private static final TimeUnit TIME_UNIT = TimeUnit.SECONDS;
    private static final LinkedBlockingQueue<Runnable> WORK_QUEUE = new LinkedBlockingQueue<>();

    //CONSTRUCTOR
    private Executor() {
        threadPoolExecutor = new ThreadPoolExecutor(
                CORE_POOL_SIZE,
                MAX_POOL_SIZE,
                KEEP_ALIVE_TIME,
                TIME_UNIT,
                WORK_QUEUE);
    }

    //INTERFACE IMPLEMENTATION: IExecutor
    @Override
    public void execute(final AbstractUseCase abstractUseCase) {
        threadPoolExecutor.submit(new Runnable() {
            @Override
            public void run() {
                abstractUseCase.run();
                abstractUseCase.onFinished();
            }
        });
    }

    //CLASS METHODS
    /**
     * Returns a singleton instance of this executor. If the executor is not initialized then it
     * initializes it and returns the instance.
     * @return singleton instance of Executor.
     */
    public static IExecutor getInstance() {
        if (singletonExecutor == null) singletonExecutor = new Executor();
        return singletonExecutor;
    }
}