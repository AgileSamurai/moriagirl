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
    public ObservableField<String>  motion;
    public ObservableField<String>  text;

    private double minDuration;
    private double maxDuration;

    private TimerModel            timer;
    private ActionControllerModel actionController;

    private CompositeSubscription subscriptions = new CompositeSubscription();

    public TopicViewModel(LivelyLevelMeterModel livelyLevelMeter) {
        timer.start();
        livelyLevelMeter.setLivelyLevelDeterminer(
            LivelyLevelDeterminerProvider.getDefaultStaticAverageDeterminer());
        // Change action
        int timespan = 20;
        int timeshift = 10;
        Subscription sub = livelyLevelMeter.getLivelyLevel(timespan, timeshift, TimeUnit.SECONDS)
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(this::changeAction);
        subscriptions.add(sub);
    }

    private void changeAction(LivelyLevel level) {
        if (level.ordinal() < LivelyLevel.Middle.ordinal()) {
            if (timer.getTimeSecond() < minDuration) {
                // lively level is low, and duration is lower than min_duration
                receiveAndApplyAction(actionController.getReaction(level));
            } else {
                // lively level is low, and duration is larger than min_duration
                receiveAndApplyAction(actionController.getTopic(level));
            }
        } else {
            if (timer.getTimeSecond() < maxDuration) {
                // lively level is high, and duration is lower than min_duration
                receiveAndApplyAction(actionController.getReaction(level));
            } else {
                // lively level is high, and duration is larger than min_duration
                receiveAndApplyAction(actionController.getTopic(level));
            }
        }
    }

    private void receiveAndApplyAction(TopicAction action) {
        motion.set(action.getMotion());
        text.set(action.getText());
        minDuration = action.getMinDuration();
        maxDuration = action.getMaxDuration();
        // Restart timer
        timer.start();
    }

    private void receiveAndApplyAction(ReactionAction action) {
        motion.set(action.getMotion());
        text.set(action.getText());
    }

    public void unsubscribe() {
        subscriptions.unsubscribe();
    }
}
