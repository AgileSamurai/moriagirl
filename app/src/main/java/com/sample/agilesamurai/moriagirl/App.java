package com.sample.agilesamurai.moriagirl;

import android.app.Application;
import com.sample.agilesamurai.moriagirl.models.LivelyLevelMeterModel;
import com.sample.agilesamurai.moriagirl.models.SoundMeterModel;

/**
 * Created by ibara on 2016/11/13.
 */

public class App extends Application {
    private LivelyLevelMeterModel llm;
    private SoundMeterModel smm;

    @Override
    public void onCreate() {
        super.onCreate();
        smm = new SoundMeterModel();
        llm = new LivelyLevelMeterModel(smm);

        smm.start();
    }

    @Override
    public void onTerminate() {
        smm.stop();
        super.onTerminate();
    }

    public SoundMeterModel getSoundMeter() {
        return smm;
    }
    public LivelyLevelMeterModel getLivelyLevelMeter() {
        return llm;
    }
}
