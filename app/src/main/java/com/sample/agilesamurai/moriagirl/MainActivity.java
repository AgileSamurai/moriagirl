package com.sample.agilesamurai.moriagirl;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    static List<String> name = new ArrayList();
    Button syokaiButton;
    Speeching speeching;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        syokaiButton = (Button)findViewById(R.id.syokai);
        speeching = new Speeching(this);
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        speeching.shutDown();
    }

    public void displayInputMessage(View view){
        displayMessage(getString(R.string.inputName));
    }

    public void inputName(View view){
        AlertDialog.Builder ad=new AlertDialog.Builder(this);
        ad.setTitle(R.string.name);
        ad.setIcon(R.drawable.asd);
        final EditText input = new EditText(MainActivity.this);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        input.setLayoutParams(lp);
        ad.setView(input);
        ad.setPositiveButton(R.string.signIn, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                String currentName = input.getText().toString();
                name.add(currentName);
                syokaiButton.setVisibility(View.VISIBLE);
                Toast.makeText(getApplicationContext(), currentName + "登録しました～",
                        Toast.LENGTH_SHORT).show();
            }
        });
        ad.setNegativeButton(R.string.cancel ,new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                dialog.dismiss();
            }
        });
        ad.create();
        ad.show();
    }

    public void selfIntroduction(View view){
        SelfIntroduction selfIntroduction = new SelfIntroduction(name, this);
        selfIntroduction.introduction();
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
