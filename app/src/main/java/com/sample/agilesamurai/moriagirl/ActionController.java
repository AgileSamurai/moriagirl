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

    String[] textList = {"僕の趣味は寝ることだよ．君たちの趣味を教えてよ", "昨日あった出来事の中で一番印象に残ってるものを教えてよ．"};
    int listSize = textList.length;

    ActionController(Activity context_input) {
        text = "";
        activity = context_input;
        talking = new Talking(activity);
        Action.InitRoot(context_input);
        this.action = new Action();
    }

    public int putTopic(List<String> name, Action.ActionType type){
        System.out.println("a");
        count++;
        System.out.println("b");
        text = action.text;
        System.out.println("c");
        talking.talk(text);
        System.out.println("d");
        return count;
    }
}
