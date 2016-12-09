package com.sample.agilesamurai.moriagirl.utils;

import com.sample.agilesamurai.moriagirl.models.LivelyLevelMeterModel;
import com.sample.agilesamurai.moriagirl.models.LivelyLevelMeterModel.LivelyLevel;
import com.sample.agilesamurai.moriagirl.models.SoundMeterModel;

import java.util.List;

import rx.Observable;
import rx.observables.MathObservable;

/**
 * Created by ibara on 2016/12/05.
 */

public class LivelyLevelDeterminerProvider {
    static public LivelyLevelDeterminer getDefaultStaticAverageDeterminer() {
        double mean = SoundMeterModel.LOUDNESS_MAX_VALUE / 4;  // Guess 1/4 is the mean value of normal sound level
        double ratio = 1.3;
        return getStaticAverageDeterminer(mean, ratio);
    }

    static public LivelyLevelDeterminer getStaticAverageDeterminer(double middle, double ratio) {
        double ratio2 = ratio * ratio;
        return new LivelyLevelDeterminer() {
            @Override
            public LivelyLevel call(List<Integer> volumes) {
                int size = volumes.size();
                double average = MathObservable.sumInteger(Observable.from(volumes))
                    .map(sum -> sum.doubleValue() / size) // Convert sum to double to calculate average in double
                    .toBlocking().single();

                if (average > middle * ratio2) {
                    return LivelyLevel.VeryHigh;
                }
                else if(average > middle * ratio) {
                    return LivelyLevel.High;
                }
                else if(average > middle * (2 - ratio)) {
                    return LivelyLevel.Middle;
                }
                else if(average > middle * (2 - ratio2)) {
                    return LivelyLevel.Low;
                }
                else {
                    return LivelyLevel.VeryLow;
                }
            }
        };
    }
}
