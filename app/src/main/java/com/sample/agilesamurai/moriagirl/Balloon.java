package com.sample.agilesamurai.moriagirl;

import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

/**
 * Created by コウ on 2016/8/29.
 */
public class Balloon  {
    private Window mWindow;
    
    public Window getWindow() {
        return mWindow;
    }

    protected void show(String message) {
        TextView priceTextView = (TextView) findViewById(R.id.mikuText);
        priceTextView.setText(message);
    }

    public View findViewById(@IdRes int id) {
        return getWindow().findViewById(id);
    }
}
