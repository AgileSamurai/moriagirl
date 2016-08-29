package com.sample.agilesamurai.moriagirl;

import android.app.Activity;
import android.content.Context;

/**
 * Created by ito on 2016/08/29.
 */
public class Greeting {
    Talking talking;

    String grl;
    public Greeting(Activity context_input){
        grl = "こんにちは、もりあガールです";
        talking = new Talking(context_input);
    }

    public void startGreeting(){
        talking.talk(grl);
    }

    public void randomGreeting(){
        //TopicPutterのrandomTextPutのtopic.txtを変えればすぐできるが・・・
    }
}
