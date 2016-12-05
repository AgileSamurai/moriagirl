package com.sample.agilesamurai.moriagirl.utils;

/**
 * Created by ibara on 2016/12/04.
 */

public abstract class Action {
    private String emotion;
    private Double minDuration;
    private Double maxDuration;
    private String soundEffect;

    public Action(String emotion, Double minDuration, Double maxDuration, String soundEffect) {
        this.emotion     = emotion;
        this.minDuration = minDuration;
        this.maxDuration = maxDuration;
        this.soundEffect = soundEffect;
    }

    public String getEmotion() {
        return this.emotion;
    }

    public Double getMinDuration() {
        return this.minDuration;
    }

    public Double getMaxDuration() {
        return this.maxDuration;
    }

    public String getSoundEffect() {
        return this.soundEffect;
    }
}
