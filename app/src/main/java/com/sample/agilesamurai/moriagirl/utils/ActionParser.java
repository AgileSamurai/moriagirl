package com.sample.agilesamurai.moriagirl.utils;

import com.sample.agilesamurai.moriagirl.utils.Action;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;

/**
 * Created by ibara on 2016/12/09.
 */

public class ActionParser {
    private JSONObject root;

    public ActionParser(String jsonString) throws JSONException {
        JSONObject root = new JSONObject(jsonString);
    }

    private ReactionAction parseReaction(JSONObject reactionJson) throws JSONException {
        return new ReactionAction(
            reactionJson.getString("emotion"),
            reactionJson.getInt("min_lively_level"),
            reactionJson.getInt("max_lively_level"),
            reactionJson.getString("text"),
            reactionJson.getString("speak"));
    }

    private TopicAction parseTopic(JSONObject topicJson) throws JSONException {
        return new TopicAction(
            topicJson.getString("emotion"),
            topicJson.getInt("min_lively_level"),
            topicJson.getInt("max_lively_level"),
            topicJson.getDouble("min_duration"),
            topicJson.getDouble("max_duration"),
            topicJson.getString("text"),
            topicJson.getString("speak"));
    }

    public Observable<TopicAction> getGroupTopics() throws JSONException {
        List<TopicAction> actions = new ArrayList<>();
        JSONArray groupTopic = root.getJSONArray("group_topic");
        for(int i = 0; i < groupTopic.length(); ++i) {
            TopicAction action = parseTopic(groupTopic.getJSONObject(i));
            actions.add(action);
        }
        return Observable.from(actions);
    }

    public Observable<TopicAction> getPersonalTopics() throws JSONException {
        List<TopicAction> actions = new ArrayList<>();
        JSONArray personalTopic = root.getJSONArray("personal_topic");
        for(int i = 0; i < personalTopic.length(); ++i) {
            TopicAction action = parseTopic(personalTopic.getJSONObject(i));
            actions.add(action);
        }
        return Observable.from(actions);
    }

    public Observable<ReactionAction> getReactions() throws JSONException {
        List<ReactionAction> actions = new ArrayList<>();
        JSONArray reactions = root.getJSONArray("reaction");
        for(int i = 0; i < reactions.length(); ++i) {
            ReactionAction action = parseReaction(reactions.getJSONObject(i));
            actions.add(action);
        }
        return Observable.from(actions);
    }
}
