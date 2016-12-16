package com.sample.agilesamurai.moriagirl.utils;

/**
 * Created by ibara on 2016/12/05.
 */

public class TopicAction extends Action {
    private Double minDuration;
    private Double maxDuration;
    private String text;
    private String speak;

    public TopicAction(String emotion, String minLivelyLevel, String maxLivelyLevel,
                       Double minDuration, Double maxDuration,
                       String text, String speak) {
        super(emotion, minLivelyLevel, maxLivelyLevel, text, speak);
        this.minDuration = minDuration;
        this.maxDuration = maxDuration;
    }

    public Double getMinDuration() {
        return this.minDuration;
    }

    public Double getMaxDuration() {
        return this.maxDuration;
    }
}
