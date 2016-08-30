package com.sample.agilesamurai.moriagirl;

import android.app.Activity;
import android.content.Context;
import java.io.InputStreamReader;
import java.io.InputStream;
import java.io.BufferedReader;
import java.util.Random;


/**
 * Created by ito on 2016/08/26.
 */
public class TopicPutter {
    String text;
    Activity activity;
    Talking talking;
    TextRead inputfile;

    TopicPutter(Activity context_input) {
        text = "";
        activity = context_input;
        talking = new Talking(activity);
        inputfile = new TextRead(context_input,"topic.txt");
    }

    public String textContents(){
        return text;
    }


    //とりあえず１行目のお題表示
    public void textPut(){
        text = inputfile.simpleTextRead(1);
        talking.talk(text);
    }

    //10行の中でランダムで１行表示
    public void randomTextPut(){
        text = inputfile.randomTextRead();
        talking.talk(text);
    }

}
