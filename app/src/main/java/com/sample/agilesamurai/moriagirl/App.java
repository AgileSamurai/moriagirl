package com.sample.agilesamurai.moriagirl;

import android.app.Application;

import com.sample.agilesamurai.moriagirl.models.ActionControllerModel;
import com.sample.agilesamurai.moriagirl.models.LivelyLevelMeterModel;
import com.sample.agilesamurai.moriagirl.models.SoundMeterModel;
import com.sample.agilesamurai.moriagirl.models.TimerModel;

/**
 * Created by ibara on 2016/11/13.
 */

public class App extends Application {
    private LivelyLevelMeterModel llm;
    private SoundMeterModel smm;
    private ActionControllerModel acm;
    private TimerModel tm;
    private Speaking sm;

    @Override
    public void onCreate() {
        super.onCreate();
        smm = new SoundMeterModel();
        llm = new LivelyLevelMeterModel(smm);
        acm = new ActionControllerModel(this.getApplicationContext());
        tm  = new TimerModel();
        sm  = new Speaking(this);
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

    public ActionControllerModel getActionController() {
        return acm;
    }

    public TimerModel getTimer() {
        return tm;
    }

    public Speaking getSpeaker() {
        return sm;
    }
}
