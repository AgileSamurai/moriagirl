package com.sample.agilesamurai.moriagirl;


import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.media.AudioManager;
import android.media.MediaPlayer;

import java.io.IOException;

/**
 * Created by takahashitomokatsu on 2016/08/29.
 */
public class titleActivity extends AppCompatActivity {
    Button startButton;
    Intent intent = new Intent();
    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //savedInstanceState,これはデフォルトではnullだが画面の回転によるonCreateの呼び出しだとnullじゃない
        super.onCreate(savedInstanceState);//親クラスのonCreateを呼び出し？
        // 音楽再生
        audioPlay();
        if(savedInstanceState == null) {
            setContentView(R.layout.title);//アプリの画面設定を読み込み
            //syokaiButton = (Button)findViewById(R.id.syokai);
            startButton = (Button) findViewById(R.id.startapp);
        }else {
            setContentView(R.layout.title);
            startButton = (Button) findViewById(R.id.startapp);
        }
    }

    private boolean audioSetup(){
        boolean fileCheck = false;

        // インタンスを生成
        mediaPlayer = new MediaPlayer();

        //音楽ファイル名, あるいはパス
        String filePath = "abc.mp3";

        try {
            // assetsから mp3 ファイルを読み込み
            AssetFileDescriptor afdescripter = getAssets().openFd(filePath);
            // MediaPlayerに読み込んだ音楽ファイルを指定
            mediaPlayer.setDataSource(afdescripter.getFileDescriptor(),
                    afdescripter.getStartOffset(),
                    afdescripter.getLength());
            // 音量調整を端末のボタンに任せる
            setVolumeControlStream(AudioManager.STREAM_MUSIC);
            mediaPlayer.prepare();
            fileCheck = true;
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        return fileCheck;
    }

    private void audioPlay() {

        if (mediaPlayer == null) {
            // audio ファイルを読出し
            if (audioSetup()){
                Toast.makeText(getApplication(), "Rread audio file", Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(getApplication(), "Error: read audio file", Toast.LENGTH_SHORT).show();
                return;
            }
        }
        else{
            // 繰り返し再生する場合
            mediaPlayer.stop();
            mediaPlayer.reset();
            // リソースの解放
            mediaPlayer.release();
        }

        // 再生する
        mediaPlayer.start();

        // 終了を検知するリスナー
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            public void onCompletion(MediaPlayer mp) {
                mediaPlayer.start();
            }
        });
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
