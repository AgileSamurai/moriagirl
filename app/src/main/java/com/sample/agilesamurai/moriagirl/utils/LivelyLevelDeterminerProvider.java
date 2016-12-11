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
    private interface DetermineFunction {
        public LivelyLevel call(double volume);
    }

    static private DetermineFunction getDetermineFunction(double middle, double ratio) {
        return new DetermineFunction() {
            @Override
            public LivelyLevel call(double volume) {
                if (volume > middle * ratio * ratio) {
                    return LivelyLevel.VeryHigh;
                }
                else if(volume > middle * ratio) {
                    return LivelyLevel.High;
                }
                else if(volume > middle * (2 - ratio)) {
                    return LivelyLevel.Middle;
                }
                else if(volume > middle * (2 - ratio * ratio)) {
                    return LivelyLevel.Low;
                }
                else {
                    return LivelyLevel.VeryLow;
                }
            }
        };
    }

    static public LivelyLevelDeterminer getDefaultStaticAverageDeterminer() {
        double mean = SoundMeterModel.LOUDNESS_MAX_VALUE / 10;  // Guess 1/10 is the mean value of normal sound level
        double ratio = 1.3;
        return getStaticAverageDeterminer(mean, ratio);
    }

    static public LivelyLevelDeterminer getStaticAverageDeterminer(double middle, double ratio) {
        DetermineFunction det = getDetermineFunction(middle, ratio);
        return new LivelyLevelDeterminer() {
            @Override
            public LivelyLevel call(List<Integer> volumes) {
                int size = volumes.size();
                double average = MathObservable.sumInteger(Observable.from(volumes))
                    .map(sum -> sum.doubleValue() / size) // Convert sum to double so we can calculate average
                    .toBlocking().single();
                return det.call(average);
            }
        };
    }
}
