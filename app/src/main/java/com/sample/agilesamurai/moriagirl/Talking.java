package com.sample.agilesamurai.moriagirl;

import android.app.Activity;
import android.content.Context;

/**
 * Created by motoki on 2016/08/29.
 */
public class Talking {

    Balloon balloon;
    Speeching speeching;
    public Talking(Activity activity){
        balloon = new Balloon(activity);
        speeching = new Speeching(activity);
    }

    /**
     * 吹き出しと文字を表示して、声に出す
     * @param text 話すテキスト
     */
    public void talk(String text){
        balloon.show(text);
        speeching.speechText(text);
    }

    /**
     * 終了処理
     * 最後には必ず呼び出す
     */
    public void shutDown(){
        speeching.shutDown();
    }
}
