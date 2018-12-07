package ru.neurospb.notes.ui.views.base;

import android.support.design.widget.Snackbar;
import android.support.v4.widget.NestedScrollView;
import android.view.LayoutInflater;

import ru.neurospb.notes.R;
import ru.neurospb.notes.ui.views.StartPoint;

/**
 * Implements common methods and common setup for views.
 *
 * @author Alexey Utovka
 * @version alpha1
 * @since alpha1
 */

public class AbstractView implements IbaseView {
    private int layoutId;
    private LayoutInflater layoutInflater;
    private NestedScrollView layoutWrapper;

    //CONSTRUCTOR
    public AbstractView(int layoutId) {
        this.layoutId = layoutId;
        layoutInflater = StartPoint.getActivity().getLayoutInflater();
        layoutWrapper = StartPoint.getActivity().findViewById(R.id.wrapper);
    }

    //INTERFACE IMPLEMENTATION: IbaseView
    @Override
    public void onMessage (String message) {
        Snackbar msg = Snackbar.make(layoutWrapper, message, Snackbar.LENGTH_LONG);
        msg.show();
    }

    //CLASS METHODS
    /**
     * Renders this view.
     */
    protected void renderSelf() {
        layoutWrapper.removeAllViews();
        layoutInflater.inflate(layoutId, layoutWrapper);
    }
}