package com.sample.agilesamurai.moriagirl;

import android.content.Context;

/**
 * Created by motoki on 2016/08/29.
 */
public class Talking {

    // Baloon baloon;   // TODO: enable
    Speeching speeching;
    public Talking(Context context){
        // baloon = new Baloon();   // TODO: enable
        speeching = new Speeching(context);
    }

    /**
     * 吹き出しと文字を表示して、声に出す
     * @param text 話すテキスト
     */
    public void talk(String text){
        // baloon.show(text);   // TODO: enable
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
