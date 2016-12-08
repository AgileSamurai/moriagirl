package com.sample.agilesamurai.moriagirl;

import android.app.Activity;

import java.util.ArrayList;
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
    ActionReader actionReader;
    int count = 0;
    List<Action> pTopics = new ArrayList<>();
    List<Action> gTopics = new ArrayList<>();
    List<Action> reactions = new ArrayList<>();
    List<List<Integer>> reactionLevel = new ArrayList<>();

    ActionController(Activity context_input) {
        text = "";
        activity = context_input;
        talking = new Talking(activity);
        actionReader = new ActionReader(activity);
        for(int i = 0; i < 5; i++){
            reactionLevel.add(new ArrayList<>());
        }
        getActions();
    }

    public int moveAction(List<String> name) {
        Random rnd = new Random();
        int ran = rnd.nextInt(2);
        Action.ActionType actionType;
        if (ran == 0) {
            actionType = Action.ActionType.Personal;
        } else {
            actionType = Action.ActionType.Group;
        }
        return moveAction(name, actionType);
    }

    public int moveAction(List<String> name, Action.ActionType actionType) {
        count++;
        Random rnd = new Random();
        int ran;
        Action action;
        if (actionType == Action.ActionType.Personal) {
            ran = rnd.nextInt(pTopics.size());
            action = pTopics.get(ran);
        } else {
            ran = rnd.nextInt(gTopics.size());
            action = gTopics.get(ran);
        }
        text = action.text;
        speak = action.speak;
        if (actionType == Action.ActionType.Personal) {
            text = text.replaceFirst("NAME", name.get(ran));
            speak = speak.replaceFirst("NAME", name.get(ran));
        }
        talking.talk(text, speak);
        return count;
    }

    //randomにreaction
    public void moveAction(int feverLevel) {
        Random rnd = new Random();
        int ran = rnd.nextInt(reactionLevel.get(feverLevel).size());
        text = reactions.get(reactionLevel.get(feverLevel).get(ran)).text;
        speak = reactions.get(reactionLevel.get(feverLevel).get(ran)).text;
        talking.talk(text, speak);
    }

    public void getActions() {
        for (int i = 0; i < actionReader.getPersonalTopicLength(); i++) {
            pTopics.add(actionReader.readAction(Action.ActionType.Personal, i));
        }
        for (int i = 0; i < actionReader.getGroupTopicLength(); i++) {
            gTopics.add(actionReader.readAction(Action.ActionType.Group, i));
        }
        for (int i = 0; i < actionReader.getReactionLength(); i++) {
            reactions.add(actionReader.readAction(Action.ActionType.Reaction, i));
            int minFever = reactions.get(i).minFever;
            int maxFever = reactions.get(i).maxFever;

            if (minFever <= 0) {
                reactionLevel.get(0).add(i);
            }
            if (minFever <= 1 && maxFever >= 1) {
                reactionLevel.get(1).add(i);
            }
            if (minFever <= 2 && maxFever >= 2) {
                reactionLevel.get(2).add(i);
            }
            if (minFever <= 3 && maxFever >= 3) {
                reactionLevel.get(3).add(i);
            }
            if (maxFever >= 4) {
                reactionLevel.get(4).add(i);
            }

        }
    }
}
