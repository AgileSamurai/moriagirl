package com.sample.agilesamurai.moriagirl;

import android.app.Application;

import com.sample.agilesamurai.moriagirl.models.SoundMeterDataModel;
import com.sample.agilesamurai.moriagirl.models.SoundMeterModel;

import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by ibara on 2016/11/13.
 */

public class App extends Application {
    private SoundMeterModel soundMeter;
    private SoundMeterDataModel soundMeterData;

    CompositeSubscription subscriptions = new CompositeSubscription();

    @Override
    public void onCreate() {
        super.onCreate();
        soundMeter = new SoundMeterModel();
        soundMeterData = new SoundMeterDataModel();
        subscriptions.add(soundMeter.getOutStream()
                .subscribeOn(Schedulers.newThread()) // Schedulers.io() uses thread pool, whereas Schedulers.newThread() does not
                .observeOn(Schedulers.newThread())
                .subscribe(soundMeterData.getInStream()));
        soundMeter.start();
    }

    @Override
    public void onTerminate() {
        subscriptions.unsubscribe();
        soundMeter.stop();
        super.onTerminate();
    }

    public SoundMeterModel getSoundMeterModel() {
        return soundMeter;
    }

    public SoundMeterDataModel getSoundMeterDataModel() {
        return soundMeterData;
    }
}
