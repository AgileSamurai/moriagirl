package com.sample.agilesamurai.moriagirl;

import android.app.Activity;
import android.content.res.AssetManager;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 * Created by kusamura on 2016/12/08.
 */
public class ActionReader {
    private static ObjectMapper mapper;
    private static JsonNode root;
    private static Activity activity;
    private static int groupTopicLength;
    private static int personalTopicLength;
    private static int reactionLength;


    ActionReader(Action.ActionType, int number, Activity context_input) {
        try {
            activity = context_input;
            AssetManager as = activity.getAssets();
            mapper = new ObjectMapper();
            root = mapper.readTree(as.open("Actions.json"));

        } catch (IOException e) {
            System.out.println("InitRoot_exception");
        }

        groupTopicLength = root.get("GroupTopic").size();
        personalTopicLength = root.get("PersonalTopic").size();
        reactionLength = root.get("reaction").size();
    }

    readAction(Action.ActionType actionType, int number) {
        String category;
        category = "";
        int lengthOver = 0;
        switch (actionType) {
            case Personal:
                category = "PersonalTopic";
                if (personalTopicLength <= number) lengthOver = 1;
                break;
            case Group:
                category = "GroupTopic";
                if (groupTopicLength <= number) lengthOver = 1;
                break;
            case Reaction:
                category = "reaction";
                if (groupTopicLength <= number) lengthOver = 1;
                break;
        }

        if (lengthOver == 0) {
            return Action(
                    actionType,
                    root.get(category).get(number).get("emotion").asText(),
                    root.get(category).get(number).get("min_duration").asInt(),
                    root.get(category).get(number).get("max_duration").asInt(),
                    root.get(category).get(number).get("sound_effect").asText(),
                    root.get(category).get(number).get("type").asText(),
                    root.get(category).get(number).get("params").get("text").asText(),
                    root.get(category).get(number).get("params").get("speak").asText(),
                    root.get(category).get(number).get("params").get("volume").asDouble(),
                    root.get(category).get(number).get("params").get("speed").asDouble()
            );
        } else {
            System.out.println("存在しない番号の" + category + "です");
        }
    }
}
