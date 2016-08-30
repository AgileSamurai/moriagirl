package com.sample.agilesamurai.moriagirl;

import android.app.Activity;
import android.content.Context;

/**
 * Created by ito on 2016/08/29.
 */
public class Greeting {
    Talking talking;

    String grl;
    TextRead inputfile;

    public Greeting(Activity context_input){
       // gr1 = "こんにちは、もりあガールです";
        talking = new Talking(context_input);
        inputfile = new TextRead(context_input,"greeting.txt");
    }

    public void startGreeting(){
        grl = inputfile.simpleTextRead(1);
        talking.talk(grl);
    }

    public String startGreetingReturn(){
        grl = inputfile.simpleTextRead(1);
        //talking.talk(grl);
        return grl;
    }

    public void randomGreeting(){
        grl = inputfile.randomTextRead();
        talking.talk(grl);
    }

    public String randomGreetingReturn(){
        grl = inputfile.randomTextRead();
        //talking.talk(grl);
        return grl;
    }
}
