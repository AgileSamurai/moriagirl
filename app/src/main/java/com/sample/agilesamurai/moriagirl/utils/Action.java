package com.sample.agilesamurai.moriagirl.utils;

/**
 * Created by ibara on 2016/12/04.
 */

public abstract class Action {
    private String motion;
    private Double minDuration;
    private Double maxDuration;
    private Integer minLivelyLevel;
    private Integer maxLivelyLevel;

    public Action(String motion, Double minDuration, Double maxDuration, Integer minLivelyLevel, Integer maxLivelyLevel) {
        this.motion      = motion;
        this.minDuration = minDuration;
        this.maxDuration = maxDuration;
        this.minLivelyLevel = minLivelyLevel;
        this.maxLivelyLevel = maxLivelyLevel;
    }

    public String getEmotion() {
        return this.motion;
    }

    public Double getMinDuration() {
        return this.minDuration;
    }

    public Double getMaxDuration() {
        return this.maxDuration;
    }

    public Integer getMinLivelyLevel() {
        return this.minLivelyLevel;
    }

    public Integer getMaxLivelyLevel() {
        return this.maxLivelyLevel;
    }

    /**
     * Check {@level} is available for this Action.json
     * @param level the level we want to check
     * @return boolean value, which represents level is in this interval or not
     */
    public boolean inLivelyLevel(int level) {
        return minLivelyLevel < level && level > maxLivelyLevel;
    }
}
