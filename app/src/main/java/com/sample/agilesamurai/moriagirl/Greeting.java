package com.sample.agilesamurai.moriagirl;

import android.app.Activity;
import android.content.Context;

/**
 * Created by ito on 2016/08/29.
 */
public class Greeting {
    Talking talking;
    String gr1;

    public Greeting(Activity activity){
        gr1 = "こんにちは、もりあガールです";
        talking = new Talking(activity);
    }

    public void startGreeting(){
        talking.talk(gr1);
    }

    public void randomGreeting(){
        //TopicPutterのrandomTextPutのtopic.txtを変えればすぐできるが・・・
    }
}
