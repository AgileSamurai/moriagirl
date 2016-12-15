package com.sample.agilesamurai.moriagirl.models;

import android.content.Context;
import android.content.res.AssetManager;

import com.sample.agilesamurai.moriagirl.utils.ActionParser;
import com.sample.agilesamurai.moriagirl.utils.LivelyLevel;
import com.sample.agilesamurai.moriagirl.utils.ReactionAction;
import com.sample.agilesamurai.moriagirl.utils.TopicAction;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import rx.Observable;
import rx.observables.StringObservable;

/**
 * Created by ibara on 2016/12/12.
 */

public class ActionControllerModel {
    private Context context;

    private List<TopicAction>    topicStock;
    private List<ReactionAction> reactionStock;

    public ActionControllerModel(Context context) throws IOException, JSONException {
        this.context = context;
        init();
    }

    public void init() throws IOException, JSONException {
        AssetManager asset = context.getAssets();
        String jsonString;
        try(InputStream stream = asset.open("Actions.json");
            InputStreamReader reader = new InputStreamReader(stream);
            BufferedReader buffer = new BufferedReader(reader)) {
            jsonString =StringObservable.stringConcat(StringObservable.from(buffer))
                .toBlocking().single();
        }
        ActionParser parser = new ActionParser(jsonString);
        // TODO: Use LinkedList
        topicStock    = parser.getGroupTopics().toList().toBlocking().single();
        reactionStock = parser.getReactions().toList().toBlocking().single();

        Collections.shuffle(topicStock);
        Collections.shuffle(reactionStock);
    }

    public boolean hasTopic() {
        return topicStock.size() != 0;
    }

    public boolean hasTopic(LivelyLevel level) {
        return Observable.from(topicStock)
            .filter(topicAction -> topicAction.inLivelyLevel(level))
            .count()
            .toBlocking().single() != 0;
    }

    public TopicAction getTopic(LivelyLevel level) {
        int index = 0;
        for(; index < topicStock.size(); ++index) {
            if (topicStock.get(index).inLivelyLevel(level)) {
                break;
            }
        }
        TopicAction ret = topicStock.get(index);
        topicStock.remove(index);
        return ret;
    }

    public ReactionAction getReaction(LivelyLevel level) {
        int index = 0;
        for(; index < reactionStock.size(); ++index) {
            if (reactionStock.get(index).inLivelyLevel(level)) {
                break;
            }
        }
        ReactionAction ret = reactionStock.get(index);
        topicStock.remove(index);
        reactionStock.add(ret);
        return ret;
    }
}
