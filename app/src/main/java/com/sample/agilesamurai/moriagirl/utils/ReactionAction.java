package com.sample.agilesamurai.moriagirl.utils;

/**
 * Created by ibara1454 on 2016/12/06.
 */

public class ReactionAction extends Action {
    public ReactionAction(String emotion, Double minDuration, Double maxDuration, Integer minLivelyLevel, Integer maxLivelyLevel) {
        super(emotion, minDuration, maxDuration, minLivelyLevel, maxLivelyLevel);
    }
}
