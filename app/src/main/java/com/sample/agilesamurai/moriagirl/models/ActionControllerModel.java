package com.sample.agilesamurai.moriagirl.models;

import android.content.Context;
import android.content.res.AssetManager;

import com.sample.agilesamurai.moriagirl.utils.ActionParser;
import com.sample.agilesamurai.moriagirl.utils.LivelyLevel;
import com.sample.agilesamurai.moriagirl.utils.ReactionAction;
import com.sample.agilesamurai.moriagirl.utils.TopicAction;

import org.json.JSONException;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import rx.observables.StringObservable;

/**
 * Created by ibara on 2016/12/12.
 */

public class ActionControllerModel {
    private Context context;

    private List<TopicAction>    topicStock;
    private List<ReactionAction> reactionStock;

    public ActionControllerModel(Context context) {
        this.context = context;
    }

    public void readActions() throws IOException, JSONException {
        AssetManager asset = context.getAssets();
        String jsonString;
        try(InputStream stream = asset.open("Actions.json");
            InputStreamReader reader = new InputStreamReader(stream)) {
            jsonString = StringObservable.stringConcat(StringObservable.from(reader))
                .toBlocking().single();
        }
        ActionParser parser = new ActionParser(jsonString);
        topicStock    = parser.getGroupTopics();
        reactionStock = parser.getReactions();
        // TODO: shuffle
    }

    public boolean hasTopic() {
        return topicStock.size() != 0;
    }

    public TopicAction getTopic(LivelyLevel level) {
        TopicAction topic = topicStock.get(0);
        topicStock.remove(0);
        return topic;
    }

    public ReactionAction getReaction(LivelyLevel level) {

    }
}
