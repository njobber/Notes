package ru.neurospb.notes.ui.multithreading;

import android.os.Handler;
import android.os.Looper;

import ru.neurospb.notes.core.multithreading.IExecutor;

/**
 * Ensures that the runnable we provide will be run on the UI thread.
 *
 * @author Alexey Utovka
 * @version alpha1
 * @since alpha1
 */

public class Thread implements IExecutor.UIthread {
    private Handler mHandler;
    private static Thread thread;

    //CONSTRUCTOR
    private Thread() {
        mHandler = new Handler(Looper.getMainLooper());
    }

    //INTERFACE IMPLEMENTATION: IExecutor.UIthread
    @Override
    public void post(Runnable runnable) {
        mHandler.post(runnable);
    }

    //CLASS METHODS
    /**
     * Returns an instance of UI thread. If the thread is not initialized then it
     * initializes it and returns the instance.
     * @return An instance of Thread.
     */
    public static Thread getInstance() {
        if (thread == null) {
            thread = new Thread();
        }
        return thread;
    }
}