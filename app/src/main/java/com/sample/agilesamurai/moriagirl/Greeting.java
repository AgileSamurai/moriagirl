package com.sample.agilesamurai.moriagirl;

import android.content.Context;

/**
 * Created by ito on 2016/08/29.
 */
public class Greeting {
    Talking talking;
    String gr1;
    TextRead inputfile;

    public Greeting(Context context_input){
       // gr1 = "こんにちは、もりあガールです";
        talking = new Talking(context_input);
        inputfile = new TextRead(context_input,"greeting.txt");
    }

    public void startGreeting(){
        gr1 = inputfile.simpleTextRead(0);
        talking.talk(gr1);
    }


    public void randomGreeting(){
        gr1 = inputfile.randomTextRead();
        talking.talk(gr1);
    }
}
