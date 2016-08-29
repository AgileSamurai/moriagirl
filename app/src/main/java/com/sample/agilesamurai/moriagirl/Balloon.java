package com.sample.agilesamurai.moriagirl;

import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by コウ on 2016/8/29.
 */
public class Balloon extends AppCompatActivity {
    private void displayMessage(String message) {
        TextView priceTextView = (TextView) findViewById(R.id.mikuText);
        priceTextView.setText(message);
    }
}
