package com.sample.agilesamurai.moriagirl.models;

/**
 * Created by ibara on 2016/12/13.
 */

public class TimerModel {
    private long startTimeMillis;

    public TimerModel() {
    }

    public void start() {
        startTimeMillis = System.currentTimeMillis();
    }

    public void reset() {
        // TODO: need implemented
    }

    public void pause() {
        // TODO: need implemented
    }

    public long getTimeMillis() {
        return System.currentTimeMillis() - startTimeMillis;
    }

    public int getTimeSecond() {
        return (int)(getTimeMillis() / 1000L);
    }
}
