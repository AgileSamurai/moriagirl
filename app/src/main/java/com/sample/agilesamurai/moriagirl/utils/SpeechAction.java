package com.sample.agilesamurai.moriagirl.utils;

/**
 * Created by ibara on 2016/12/05.
 */

public class SpeechAction extends Action {
    private String text;
    private String speak;
    private Double volume;
    private Double speed;

    public SpeechAction(String emotion, Double minDuration, Double maxDuration, String soundEffect, Integer minLivelyLevel, Integer maxLivelyLevel,
                        String text, String speak, Double volume, Double speed) {
        super(emotion, minDuration, maxDuration, soundEffect, minLivelyLevel, maxLivelyLevel);
        this.text   = text;
        this.speak  = speak;
        this.volume = volume;
        this.speed  = speed;
    }

    public String getText() {
        return this.text;
    }

    public String getSpeak() {
        return this.speak;
    }

    public Double getVolume() {
        return this.volume;
    }

    public Double getSpeed() {
        return this.speed;
    }
}
