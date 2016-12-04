package com.sample.agilesamurai.moriagirl;

import android.app.Activity;
import java.util.Random;
import java.util.List;


/**
 * Created by kusamura on 2016/12/04.
 */
public class ActionController {
    String text;
    Activity activity;
    Talking talking;
    Action action;
    int count = 0;

    public enum TopicType{
        Personal,
        Group
    }

    String[] textList = {"僕の趣味は寝ることだよ．君たちの趣味を教えてよ", "昨日あった出来事の中で一番印象に残ってるものを教えてよ．"};
    int listSize = textList.length;

    ActionController(Activity context_input) {
        text = "";
        activity = context_input;
        talking = new Talking(activity);
        Action acttion = new Action();
    }

    public int putTopic(List<String> name, TopicType type){
        count++;
        Random rnd = new Random();
        int ran = rnd.nextInt(2);
        if(ran == 0) {
            text = grouptopic.randomTextRead();
        }else{
            text = personaltopic.randomTextRead();
            ran = rnd.nextInt(name.size());
            text = name.get(ran) + "さん" + text;
        }
        talking.talk(text);
        return count;
    }
}
