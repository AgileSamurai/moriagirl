package com.sample.agilesamurai.moriagirl;

import android.content.DialogInterface;
import android.content.res.AssetManager;
import android.media.MediaPlayer;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    List<String> name;
    Button syokaiButton;
    MemberManager memberManager;
    Speeching speeching;
    int state = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        syokaiButton = (Button)findViewById(R.id.syokai);
        speeching = new Speeching(this);

        memberManager = new MemberManager(this);
        name = memberManager.getNames();
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        speeching.shutDown();
    }

    public void displayInputMessage(){
        displayMessage(getString(R.string.inputName));
        state = 1;
    }

    // TODO: いつタップされても入力できちゃうから直す
    public void inputName() {
        boolean apply = memberManager.inputName();
        if(!apply){
            state = 2;
        }
    }

    public void selfIntroduction() {
        if(!name.isEmpty()){
            displayMessage("では、" + name.get(0) + " さん自己紹介してください～");
            name.remove(0);
        }else {
            syokaiButton.setVisibility(View.INVISIBLE);
            displayMessage(getString(R.string.byebye));
        }
    }

    public void onClick(View view){
        switch(state) {
            case 0:
                displayInputMessage();
                break;
            case 1:
                inputName();
                break;
            case 2:
                selfIntroduction();
                break;
        }

    }

    private void displayMessage(String message) {
        TextView priceTextView = (TextView) findViewById(R.id.mikuText);
        priceTextView.setText(message);
        speech(message);
    }

    private void speech(String string){
        speeching.speechText(string);
    }
}
