package com.sample.agilesamurai.moriagirl.viewmodels;


import android.databinding.ObservableField;

import com.sample.agilesamurai.moriagirl.models.ActionControllerModel;
import com.sample.agilesamurai.moriagirl.models.LivelyLevelMeterModel;
import com.sample.agilesamurai.moriagirl.models.TimerModel;
import com.sample.agilesamurai.moriagirl.utils.Action;
import com.sample.agilesamurai.moriagirl.utils.LivelyLevel;
import com.sample.agilesamurai.moriagirl.utils.LivelyLevelDeterminerProvider;
import com.sample.agilesamurai.moriagirl.utils.ReactionAction;
import com.sample.agilesamurai.moriagirl.utils.TopicAction;

import java.util.List;
import java.util.concurrent.TimeUnit;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;
import android.databinding.BaseObservable;
import android.databinding.ObservableField;

import com.sample.agilesamurai.moriagirl.utils.Action;

/**
 * Created by ibara1454 on 2016/12/06.
 */

public class TopicViewModel {
    public ObservableField<Action> action;

    private TimerModel            timer;
    private ActionControllerModel actionController;

    private CompositeSubscription subscriptions = new CompositeSubscription();

    public TopicViewModel(LivelyLevelMeterModel livelyLevelMeter) {
        timer.start();
        livelyLevelMeter.setLivelyLevelDeterminer(
            LivelyLevelDeterminerProvider.getDefaultStaticAverageDeterminer());
        // Change action
        Subscription sub = livelyLevelMeter.getLivelyLevel(20, 10, TimeUnit.SECONDS)
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(this::changeAction);
        subscriptions.add(sub);
    }

    private void changeAction(LivelyLevel level) {
        if (level.compareTo(LivelyLevel.Middle) < 0) {
            if (timer.getTimeSecond() < action.get().getMinDuration()) {
                // lively level is low, and duration is lower than min_duration
                actionController.getReaction(level);
            }
            else {
                // lively level is low, and duration is larger than min_duration
                actionController.getTopic(level);
            }
        }
        else {
            if (timer.getTimeSecond() < action.get().getMaxDuration()) {
                // lively level is high, and duration is lower than min_duration
                actionController.getReaction(level);
            }
            else {
                // lively level is high, and duration is larger than min_duration
                actionController.getTopic(level);
            }

        }
    }

    private void changeAction(Action action) {
        this.action.set(action);
    }
    private ObservableField<Action> action;

    public TopicViewModel() {

    public void unsubscribe() {
        subscriptions.unsubscribe();
    }

    private ObservableField<Action> getAction() {
        return this.action;
    }
}
