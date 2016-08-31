package com.sample.agilesamurai.moriagirl;

import android.content.Context;
import android.os.Build;
import android.speech.tts.TextToSpeech;
import android.speech.tts.UtteranceProgressListener;
import android.util.Log;

import java.util.HashMap;
import java.util.logging.Handler;

/**
 * Created by motoki on 2016/08/26.
 * TODO: 声が端末の環境に依存しているので、指定できればした方がいい
 */
public class Speaking implements TextToSpeech.OnInitListener, Runnable {
    private TextToSpeech textToSpeech;
    private static final String TAG = "TestTTS";
    private String text;
    private boolean initialized = false;

    private Handler handler ;

    public Speaking(Context context){
        textToSpeech = new TextToSpeech(context,this);
        //handler = new Handler();

    }

    /**
     * Called to signal the completion of the TextToSpeech engine initialization.
     *
     * @param status {@link TextToSpeech#SUCCESS} or {@link TextToSpeech#ERROR}.
     */
    @Override
    public void onInit(int status) {
        // TTS初期化
        if (TextToSpeech.SUCCESS == status) {
            initialized = true;
            Log.d(TAG, "initialized");
        } else {
            initialized = false;
            Log.e(TAG, "faile to initialize");
        }
    }

    /**
     * 最後には必ず呼び出す
     */
    public void shutDown(){
        if (null != textToSpeech) {
            // to release the resource of TextToSpeech
            textToSpeech.shutdown();
        }
    }

    /**
     * テキストを読み上げる
     * @param text 読み上げるテキスト
     */
    public void speak(String text) {
        this.text = text;
        Thread thread = new Thread(this);
        thread.start();     // 別スレッドで喋らせる
    }

    public void speak(){
        if (0 < text.length()) {
            if (textToSpeech.isSpeaking()) {
                textToSpeech.stop();
            }
            setSpeechRate(1.0f);
            setSpeechPitch(1.0f);

            // textToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, null) に
            // KEY_PARAM_UTTERANCE_ID を HasMap で設定
            HashMap<String, String> map = new HashMap<String, String>();
            map.put(TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID,"messageID");

            textToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, map);
            setTtsListener();
        }
    }

    // 読み上げのスピード
    private void setSpeechRate(float rate){
        if (null != textToSpeech) {
            textToSpeech.setSpeechRate(rate);
        }
    }

    // 読み上げのピッチ
    private void setSpeechPitch(float pitch){
            textToSpeech.setPitch(pitch);
        }

    // 読み上げの始まりと終わりを取得
    private void setTtsListener(){
        // android version more than 15th
        // 市場でのシェアが15未満は数パーセントなので除外
        if (Build.VERSION.SDK_INT >= 15)
        {
            int listenerResult = textToSpeech.setOnUtteranceProgressListener(new UtteranceProgressListener()
            {
                @Override
                public void onDone(String utteranceId)
                {
                    Log.d(TAG,"progress on Done " + utteranceId);
                }

                @Override
                public void onError(String utteranceId)
                {
                    Log.d(TAG,"progress on Error " + utteranceId);
                }

                @Override
                public void onStart(String utteranceId)
                {
                    Log.d(TAG,"progress on Start " + utteranceId);
                }

            });
            if (listenerResult != TextToSpeech.SUCCESS)
            {
                Log.e(TAG, "failed to add utterance progress listener");
            }
        }
        else {
            Log.e(TAG, "Build VERSION is less than API 15");
        }

    }

    public boolean isInitialized(){
        return initialized;
    }

    /**
     * Starts executing the active part of the class' code. This method is
     * called when a thread is started that has been created with a class which
     * implements {@code Runnable}.
     */
    @Override
    public void run() {
        // 初期化されるまで待つ
        while(!isInitialized()) ;

        // しゃべる
        speak();
    }
}
