package ru.neurospb.notes.ui.views;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import ru.neurospb.notes.R;
import ru.neurospb.notes.ui.presenters.toolbar.ToolbarPresenter;
import ru.neurospb.notes.ui.presenters.toolbar.IToolbar;

/**
 * Start point of the application.
 *
 * @author Alexey Utovka
 * @version alpha1
 * @since alpha1
 */

public class StartPoint extends AppCompatActivity {
    private static volatile StartPoint singletonActivity; // This is a singleton
    private IToolbar.Presenter toolbar;

    //ACTIVITY LIFECYCLE CALLBACKS
    //OnCreate
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        singletonActivity = this;
        setContentView(R.layout.init);
        toolbar = new ToolbarPresenter();
        toolbar.loadState(savedInstanceState);
    }
    //OnStart / OnRestart
    //OnResume
    @Override
    protected void onResume() {
        super.onResume();
        toolbar.resume();
    }
    //OnPause
    @Override
    protected void onPause() {
        super.onPause();
        toolbar.pause();
    }
    //OnStop
    //OnDestroy
    protected void onDestroy() {
        singletonActivity = null;
        super.onDestroy();
    }

    //ACTIVITY CALLBACKS
    //onSaveInstanceState
    @Override
    protected void onSaveInstanceState(Bundle state) {
        toolbar.saveState(state);
        super.onSaveInstanceState(state);
    }
    //onCreateOptionsMenu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return toolbar.onCreateOptionsMenu(menu);
    }
    //onOptionsItemSelected
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return toolbar.onMenuAction(item);
    }

    //CLASS METHODS
    /**
     * Gets a singleton instance of this Activity.
     * Used to access to Context mainly
     * @return An instance of StartPoint
     */
    public static StartPoint getActivity() {
        return singletonActivity;
    }
}