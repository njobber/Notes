package ru.neurospb.notes.ui.views.toolbar;

import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.Menu;

import ru.neurospb.notes.R;
import ru.neurospb.notes.ui.presenters.toolbar.IToolbar;
import ru.neurospb.notes.ui.views.StartPoint;

/**
 * Shows toolbar.
 *
 * @author Alexey Utovka
 * @version alpha1
 * @since alpha1
 */

public class ToolbarView implements IToolbar.View {
    private ActionBar actionBar;

    //CONSTRUCTOR
    public ToolbarView() {
        Toolbar toolbar = StartPoint.getActivity().findViewById(R.id.toolbar);
        StartPoint.getActivity().setSupportActionBar(toolbar);
        actionBar = StartPoint.getActivity().getSupportActionBar();
    }

    //INTERFACE IMPLEMENTATION: IToolbar.View
    @Override
    public boolean prepareMenu(Menu menu) {
        StartPoint.getActivity().getMenuInflater().inflate(R.menu.menu_toolbar, menu);
        return true;
    }
    @Override
    public void renderMode(int mode, String themeName){
        if (themeName == null) {
            actionBar.setTitle(R.string.title_root);
            actionBar.setDisplayHomeAsUpEnabled(false);
        } else {
            actionBar.setTitle(themeName);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }
}