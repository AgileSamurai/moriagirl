package com.sample.agilesamurai.moriagirl;

/**
 * Created by takahashitomokatsu on 2016/12/03.
 */
public class Action {
    public enum ActionType {
        Personal,
        Group,
        Reaction
    }

    ActionType actionType;
    String emotion;
    int minDuration;
    int maxDuration;
    String soundEffect;
    String type;
    //params
    String text;
    String speak;
    Double volume;
    Double speed;

    Action(ActionType actionType, String emotion, int minDuration, int maxDuration,
           String soundEffect, String type, String text, String speak, double volume, double speed) {
        this.actionType = actionType;
        this.emotion = emotion;
        this.minDuration = minDuration;
        this.maxDuration = maxDuration;
        this.soundEffect = soundEffect;
        this.type = type;
        this.text = text;
        this.speak = speak;
        this.volume = volume;
        this.speed = speed;
    }
}