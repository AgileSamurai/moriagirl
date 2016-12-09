package com.sample.agilesamurai.moriagirl.utils;

import com.sample.agilesamurai.moriagirl.models.LivelyLevelMeterModel;

import java.util.List;

/**
 * Created by ibara on 2016/12/05.
 */

public interface LivelyLevelDeterminer {
    public LivelyLevelMeterModel.LivelyLevel call(List<Integer> volumes);
}
