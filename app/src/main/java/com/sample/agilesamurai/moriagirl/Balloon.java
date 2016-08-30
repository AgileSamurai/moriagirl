package com.sample.agilesamurai.moriagirl;

import android.app.Activity;
import android.support.annotation.IdRes;
import android.view.View;
import android.widget.TextView;

/**
 * Created by コウ on 2016/8/29.
 */
public class Balloon {
    private Activity activity;

    public Balloon(Activity activity){
        this.activity = activity;
    }

    protected void show(String message) {
        TextView priceTextView = (TextView) findViewById(R.id.speakText);
        priceTextView.setText(message);
    }

    public View findViewById(@IdRes int id) {
        return activity.findViewById(id);
    }
}
