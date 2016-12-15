package com.sample.agilesamurai.moriagirl.utils;

/**
 * Created by ibara on 2016/12/04.
 */

public abstract class Action {
    private String motion;
    private Integer minLivelyLevel;
    private Integer maxLivelyLevel;
    private String text;
    private String speak;

    public Action(String motion, Integer minLivelyLevel, Integer maxLivelyLevel, String text, String speak) {
        this.motion         = motion;
        this.minLivelyLevel = minLivelyLevel;
        this.maxLivelyLevel = maxLivelyLevel;
        this.text   = text;
        this.speak  = speak;
    }

    public String getMotion() {
        return this.motion;
    }

    public Integer getMinLivelyLevel() {
        return this.minLivelyLevel;
    }

    public Integer getMaxLivelyLevel() {
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
        return minLivelyLevel < level.ordinal() && level.ordinal() > maxLivelyLevel;
    }
}
