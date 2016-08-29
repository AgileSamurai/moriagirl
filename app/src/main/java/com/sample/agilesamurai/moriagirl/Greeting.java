package com.sample.agilesamurai.moriagirl;

import android.content.Context;

/**
 * Created by ito on 2016/08/29.
 */
public class Greeting {
    Talking talking;

    public Greeting(Context context_input){
       talking = new Talking(context_input);
    }

    public void startGreeting(){
        talking.talk("こんにちは、もりあガールです");
    }
}
