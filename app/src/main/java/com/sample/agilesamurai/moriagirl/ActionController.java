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
    int count = 0;

    ActionController(Activity context_input) {
        text = "";
        activity = context_input;
        talking = new Talking(activity);
    }

    public int moveAction(List<String> name, Action action){
        count++;
        text = action.text;
        speak = action.speak;
        Random rnd = new Random();
        int ran = rnd.nextInt(name.size());
        if(action.actionType == Action.ActionType.Personal) {
            text = text.replaceFirst("NAME", name.get(ran));
            speak = speak.replaceFirst("NAME", name.get(ran));
        }
        talking.talk(text, speak);
        return count;
    }
}
