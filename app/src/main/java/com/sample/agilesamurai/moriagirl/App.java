package com.sample.agilesamurai.moriagirl;

import android.app.Application;

/**
 * Created by ibara on 2016/11/13.
 */

public class App extends Application {
    private ClimaxLevelMeterModel clm;

    @Override
    public void onCreate() {
        super.onCreate();
        clm = ClimaxLevelMeterModel.getInstance();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }

    public ClimaxLevelMeterModel getClimaxLevelMeter() {
        return clm;
    }
}
