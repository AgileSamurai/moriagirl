package com.sample.agilesamurai.moriagirl;

import android.app.Application;
import com.sample.agilesamurai.moriagirl.models.LivelyLevelMeterModel;

/**
 * Created by ibara on 2016/11/13.
 */

public class App extends Application {
    private LivelyLevelMeterModel llm;

    @Override
    public void onCreate() {
        super.onCreate();
        llm = LivelyLevelMeterModel.getInstance();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }

    public LivelyLevelMeterModel getLivelyLevelMeter() {
        return llm;
    }
}
