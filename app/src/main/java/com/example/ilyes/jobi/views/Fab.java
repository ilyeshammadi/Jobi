package com.example.ilyes.jobi.views;

import android.content.Context;
import android.support.design.widget.FloatingActionButton;

import com.gordonwong.materialsheetfab.AnimatedFab;

/**
 * Created by ilyes on 13/12/15.
 */
public class Fab extends FloatingActionButton implements AnimatedFab {

    public Fab(Context context) {
        super(context);
    }

    /**
     * Shows the FAB.
     */
    @Override
    public void show() {
        // TODO: Animate the FAB into view or simply set its visibility
    }

    /**
     * Shows the FAB and sets the FAB's translation.
     *
     * @param translationX translation X value
     * @param translationY translation Y value
     */
    @Override
    public void show(float translationX, float translationY) {
        // TODO: This is only needed if you want to support moving
        // the FAB around the screen.
    }

    /**
     * Hides the FAB.
     */
    @Override
    public void hide() {
        // TODO: Animate the FAB out of view or simply set its visibility
    }
}

