package ru.neurospb.notes.core.usecases.base;

import ru.neurospb.notes.core.multithreading.Executor;
import ru.neurospb.notes.core.multithreading.IExecutor;
import ru.neurospb.notes.ui.multithreading.Thread;

/**
 * The basic class for each use case.
 *
 * @author Alexey Utovka
 * @version alpha1
 * @since alpha1
 */

public abstract class AbstractUseCase implements IUseCase {
    private volatile boolean isRunning;
    private IExecutor multithreadingExecutor;
    private IExecutor.UIthread threadUI;

    //CONSTRUCTOR
    public AbstractUseCase() {
        threadUI = Thread.getInstance();
        multithreadingExecutor = Executor.getInstance();
    }

    //INTERFACE IMPLEMENTATION: IUseCase
    @Override
    public void execute() {
        this.isRunning = true;
        multithreadingExecutor.execute(this);
    }
    @Override
    public void onFinished() {
        isRunning = false;
    }

    //GETTERS / SETTERS
    //threadUI
    protected IExecutor.UIthread getThreadUI() {
        return threadUI;
    }
    //isRunning
    @SuppressWarnings("unused")
    public boolean isRunning() {
        return isRunning;
    }
}