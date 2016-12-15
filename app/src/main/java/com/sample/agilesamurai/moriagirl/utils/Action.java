package com.sample.agilesamurai.moriagirl.utils;

/**
 * Created by ibara on 2016/12/04.
 */

public abstract class Action {
    private String motion;
    private LivelyLevel minLivelyLevel;
    private LivelyLevel maxLivelyLevel;
    private String text;
    private String speak;

    public Action(String motion, String minLivelyLevel, String maxLivelyLevel, String text, String speak) {
        this.motion         = motion;
        this.minLivelyLevel = stringToLivelyLevel(minLivelyLevel);
        this.maxLivelyLevel = stringToLivelyLevel(maxLivelyLevel);
        this.text   = text;
        this.speak  = speak;
    }

    static private LivelyLevel stringToLivelyLevel(String levelString) {
        levelString = levelString.toLowerCase();
        if (levelString.equals("very_low")) {
            return LivelyLevel.VeryLow;
        }
        else if (levelString.equals("low")) {
            return LivelyLevel.Low;
        }
        else if (levelString.equals("middle")) {
            return LivelyLevel.Middle;
        }
        else if (levelString.equals("high")) {
            return LivelyLevel.High;
        }
        else if (levelString.equals("very_high")) {
            return LivelyLevel.VeryHigh;
        }
        else {
            throw new WrongLivelyLevelException("Cannot convert " + levelString + "to type LivelyLevel");
        }
    }

    public String getMotion() {
        return this.motion;
    }

    public LivelyLevel getMinLivelyLevel() {
        return this.minLivelyLevel;
    }

    public LivelyLevel getMaxLivelyLevel() {
        return this.maxLivelyLevel;
    }

    public String getText() {
        return this.text;
    }

    public String getSpeak() {
        return this.speak;
    }

    /**
     * Check {@level} is available for this Action.json
     * @param level the level we want to check
     * @return boolean value, which represents level is in this interval or not
     */
    public boolean inLivelyLevel(LivelyLevel level) {
        return minLivelyLevel.ordinal() < level.ordinal() && level.ordinal() > maxLivelyLevel.ordinal();
    }
}

class WrongLivelyLevelException extends RuntimeException {
    WrongLivelyLevelException(String error) {
        super(error);
    }
}
