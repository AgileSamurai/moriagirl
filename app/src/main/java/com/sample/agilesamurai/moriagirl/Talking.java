package com.sample.agilesamurai.moriagirl;

import android.app.Activity;

/**
 * Created by motoki on 2016/08/29.
 */
public class Talking {

    Balloon balloon;
    Speaking speaking;
    public Talking(Activity activity){
        balloon = new Balloon(activity);
        speaking = new Speaking(activity);
    }

    /**
     * 吹き出しと文字を表示して、声に出す
     * @param text 話すテキスト
     */
    public void talk(String text){
        balloon.show(text);
        speaking.speak(text);
    }

    /**
     * 終了処理
     * 最後には必ず呼び出す
     */
    public void shutDown(){
        speaking.shutDown();
    }
}
