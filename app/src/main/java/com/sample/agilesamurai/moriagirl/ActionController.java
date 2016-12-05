package com.sample.agilesamurai.moriagirl;

import android.app.Activity;
import java.util.List;
import java.util.Random;


/**
 * Created by kusamura on 2016/12/04.
 */
public class ActionController {
    String text;
    String speak;
    Activity activity;
    Talking talking;
    Action action;
    int count = 0;

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
        speak = action.speak;
        Random rnd = new Random();
        int ran = rnd.nextInt(name.size());
        if(type == Action.ActionType.Personal) {
            text = text.replaceFirst("NAME", name.get(ran));
            text = speak.replaceFirst("NAME", name.get(ran));
        }
        talking.talk(text, speak);
        action.ChangeAction(Action.ActionType.Personal);
        return count;
    }
}
