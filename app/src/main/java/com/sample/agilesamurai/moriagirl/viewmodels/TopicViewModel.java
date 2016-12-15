package com.sample.agilesamurai.moriagirl.viewmodels;


import android.databinding.ObservableField;

import com.sample.agilesamurai.moriagirl.models.ActionControllerModel;
import com.sample.agilesamurai.moriagirl.models.LivelyLevelMeterModel;
import com.sample.agilesamurai.moriagirl.models.TimerModel;
import com.sample.agilesamurai.moriagirl.utils.LivelyLevel;
import com.sample.agilesamurai.moriagirl.utils.LivelyLevelDeterminerProvider;
import com.sample.agilesamurai.moriagirl.utils.ReactionAction;
import com.sample.agilesamurai.moriagirl.utils.TopicAction;

import java.util.concurrent.TimeUnit;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by ibara1454 on 2016/12/06.
 */

public class TopicViewModel {
    public ObservableField<String>  motion;
    public ObservableField<String>  text;
    public ObservableField<Integer> livelyLevel;

    private double minDuration;
    private double maxDuration;

    private TimerModel            timer;
    private ActionControllerModel actionController;

    private CompositeSubscription subscriptions = new CompositeSubscription();

    public TopicViewModel(LivelyLevelMeterModel livelyLevelMeter,
                          ActionControllerModel actionController,
                          TimerModel timer) {
        this.actionController = actionController;
        this.timer = timer;

        this.timer.start();

        livelyLevelMeter.setLivelyLevelDeterminer(
            LivelyLevelDeterminerProvider.getDefaultStaticAverageDeterminer());
        // Change action
        int timespan = 20;
        int timeshift = 10;
        Subscription sub = livelyLevelMeter.getLivelyLevel(timespan, timeshift, TimeUnit.SECONDS)
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe((level -> {
                livelyLevel.set(level.ordinal());
                changeAction(level);
            }));
        subscriptions.add(sub);
    }

    private void changeAction(LivelyLevel level) {
        // TODO: Need refactoring
        if (level.ordinal() < LivelyLevel.Middle.ordinal()) {
            if (timer.getTimeSecond() < minDuration) {
                // lively level is low, and duration is lower than min_duration
                receiveAndApplyAction(actionController.getReaction(level));
            } else {
                // lively level is low, and duration is larger than min_duration
                if (actionController.hasTopic(level)) {
                    receiveAndApplyAction(actionController.getTopic(level));
                }
                else {
                    receiveAndApplyAction(actionController.getReaction(level));
                }
            }
        } else {
            if (timer.getTimeSecond() < maxDuration) {
                // lively level is high, and duration is lower than min_duration
                receiveAndApplyAction(actionController.getReaction(level));
            } else {
                // lively level is high, and duration is larger than min_duration
                if (actionController.hasTopic(level)) {
                    receiveAndApplyAction(actionController.getTopic(level));
                }
                else {
                    receiveAndApplyAction(actionController.getReaction(level));
                }
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
