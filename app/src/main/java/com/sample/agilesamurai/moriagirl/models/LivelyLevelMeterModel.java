package com.sample.agilesamurai.moriagirl.models;

import java.util.concurrent.TimeUnit;

import rx.Observable;

import com.sample.agilesamurai.moriagirl.utils.LivelyLevelDeterminer;

/**
 * Created by ibara on 2016/12/02.
 */

public class LivelyLevelMeterModel {
    public enum ClimaxLevel {
        VeryHigh,
        High,
        Middle,
        Low,
        VeryLow
    }

    private static LivelyLevelMeterModel instance = new LivelyLevelMeterModel();

    private SoundMeterModel sm;
    private LivelyLevelDeterminer determiner;

    private LivelyLevelMeterModel() {
        sm = SoundMeterModel.getInstance();
        sm.start();
    }

    static public LivelyLevelMeterModel getInstance() {
        return instance;
    }

    public void setClimaxLevelDeterminer(LivelyLevelDeterminer determiner) {
        this.determiner = determiner;
    }

    /**
     *
     * @param timespan
     * @param timeshift
     * @param unit
     * @return
     */
    public Observable<ClimaxLevel> getLivelyLevel(long timespan, long timeshift, TimeUnit unit) {
        return sm.getSoundLevel()
            .buffer(timespan, timeshift, unit)
            .map(determiner::call)
            .publish();  // Convert to hot observable
    }
}
