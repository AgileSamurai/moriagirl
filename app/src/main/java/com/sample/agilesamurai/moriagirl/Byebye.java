package com.sample.agilesamurai.moriagirl;

import android.app.Activity;

/**
 * Created by kusamura on 16/08/30.
 */
public class Byebye {
    Talking talking;

    String content;
    TextRead inputfile;

    public Byebye(Activity context_input){
        talking = new Talking(context_input);
        inputfile = new TextRead(context_input,"byebye.txt");
    }

    public void randomByeBye(){
        content = inputfile.randomTextRead();
        talking.talk(content);
    }
}
