package com.sample.agilesamurai.moriagirl.viewmodels;


import android.databinding.ObservableField;

import com.sample.agilesamurai.moriagirl.models.LivelyLevelMeterModel;
import com.sample.agilesamurai.moriagirl.utils.LivelyLevelDeterminerProvider;

import java.util.concurrent.TimeUnit;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by ibara1454 on 2016/12/06.
 */

public class TopicViewModel {
    public ObservableField<String>            text = new ObservableField<>();
    public ObservableField<Boolean> textVisibility = new ObservableField<>();

    public ObservableField<String> lastLevel = new ObservableField<>("N/A");
    public ObservableField<String> currLevel = new ObservableField<>("N/A");

    private CompositeSubscription subscriptions = new CompositeSubscription();

    public TopicViewModel(LivelyLevelMeterModel livelyLevelMeter) {
        livelyLevelMeter.setLivelyLevelDeterminer(
            LivelyLevelDeterminerProvider.getDefaultStaticAverageDeterminer());
        Subscription sub = livelyLevelMeter.getLivelyLevelWithLast(10, 5, TimeUnit.SECONDS)
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(pair -> {
                lastLevel.set(pair.first.toString());
                currLevel.set(pair.second.toString());
            });
        subscriptions.add(sub);
    }

    public void unsubscribe() {
        subscriptions.unsubscribe();
    }
}
