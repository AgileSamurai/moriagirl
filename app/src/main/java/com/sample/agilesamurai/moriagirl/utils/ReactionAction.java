package com.sample.agilesamurai.moriagirl.utils;

/**
 * Created by ibara1454 on 2016/12/06.
 */

public class ReactionAction extends Action {
    // TODO: Maybe Reaction need not to have text and speak
    private String text;
    private String speak;
    private Double volume;
    private Double speed;

    public ReactionAction(String motion, String minLivelyLevel, String maxLivelyLevel,
                          String text, String speak) {
        super(motion, minLivelyLevel, maxLivelyLevel, text, speak);
    }
}
