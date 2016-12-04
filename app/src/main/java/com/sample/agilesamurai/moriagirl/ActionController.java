package com.sample.agilesamurai.moriagirl;

import android.app.Activity;
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

    ActionController(Activity context_input) {
        text = "";
        activity = context_input;
        talking = new Talking(activity);
        Action.InitRoot(context_input);
        this.action = new Action(Action.ActionType.Personal);
    }

    public int putTopic(List<String> name, Action.ActionType type){
        count++;
        text = action.text;
        System.out.print(type);
        if(type == Action.ActionType.Personal) text = text.replaceFirst("NAME",name.get(0));
        //if(type == Action.ActionType.Personal) text = text.replace('m','M');
        talking.talk(text);
        action.ChangeAction(Action.ActionType.Personal);
        return count;
    }
}
