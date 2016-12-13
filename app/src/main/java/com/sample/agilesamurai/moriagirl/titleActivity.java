package com.sample.agilesamurai.moriagirl;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.View;
import android.widget.Button;

/**
 * Created by takahashitomokatsu on 2016/08/29.
 */
public class titleActivity extends AppCompatActivity {
    Button startButton;
Intent intent = new Intent();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //savedInstanceState,これはデフォルトではnullだが画面の回転によるonCreateの呼び出しだとnullじゃない
        super.onCreate(savedInstanceState);//親クラスのonCreateを呼び出し？
        if(savedInstanceState == null) {
            setContentView(R.layout.title);//アプリの画面設定を読み込み
            //syokaiButton = (Button)findViewById(R.id.syokai);
            startButton = (Button) findViewById(R.id.startapp);
        }else {
            setContentView(R.layout.title);
            startButton = (Button) findViewById(R.id.startapp);
        }
    }

    public void startUp(View view) {
        intent.setClassName("com.sample.agilesamurai.moriagirl","com.sample.agilesamurai.moriagirl.MainActivity");
        startActivity(intent);
    }

    public void config(View view) {
        intent.setClassName("com.sample.agilesamurai.moriagirl","com.sample.agilesamurai.moriagirl.SettingsActivity");
        startActivity(intent);
    }
}
