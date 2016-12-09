package com.sample.agilesamurai.moriagirl.models;

import android.support.v4.util.Pair;

import java.util.concurrent.TimeUnit;

import rx.Observable;

import com.sample.agilesamurai.moriagirl.utils.LivelyLevelDeterminer;

/**
 * Created by ibara on 2016/12/02.
 */

public class LivelyLevelMeterModel {
    public enum LivelyLevel {
        VeryHigh,
        High,
        Middle,
        Low,
        VeryLow
    }

    private SoundMeterModel sm;
    private LivelyLevelDeterminer determiner;

    public LivelyLevelMeterModel(SoundMeterModel soundMeter) {
        sm = soundMeter;
    }

    public void setLivelyLevelDeterminer(LivelyLevelDeterminer determiner) {
        this.determiner = determiner;
    }

    /** Returns the LivelyLevel of each {@code timespan}
     *
     * @param timespan span of time
     * @param timeshift
     * @param unit unit of time
     * @return LivelyLevel of each {@code timespan}
     */
    public Observable<LivelyLevel> getLivelyLevel(long timespan, long timeshift, TimeUnit unit) {
        return sm.getSoundLevel()
            .buffer(timespan, timeshift, unit)
            .map(determiner::call)
            .publish();  // Convert to hot observable
    }

    public Observable<Pair<LivelyLevel, LivelyLevel>> getLivelyLevelWithLast(long timespan, long timeshift, TimeUnit unit, LivelyLevel initLastLevel) {
        Observable<LivelyLevel> curr = getLivelyLevel(timespan, timeshift, unit);
        Observable<LivelyLevel> last = curr.startWith(initLastLevel);
        return Observable.zip(last, curr, Pair::create)
            .publish();  // Convert to hot observable
    }

    public Observable<Pair<LivelyLevel, LivelyLevel>> getLivelyLevelWithLast(long timespan, long timeshift, TimeUnit unit) {
        // Let "VeryLow" to be the first past level
        LivelyLevel initLastLevel = LivelyLevel.VeryLow;
        return getLivelyLevelWithLast(timespan, timeshift, unit, initLastLevel);
    }
}
