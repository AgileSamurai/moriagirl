package com.sample.agilesamurai.moriagirl;

import android.content.Context;
import android.content.res.AssetManager;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.security.acl.Group;
import java.util.Random;

/**
 * Created by takahashitomokatsu on 2016/12/03.
 */
public class Action {
    private static ObjectMapper mapper;
    private static JsonNode root;
    private static Context context;
    private static int groupTopicLength;
    private static int personalTopicLength;
    private static int reactionLength;

    String emotion;
    int minDuration;
    int maxDuration;
    String soundEffect;
    String type;
    //params
    String text;
    String speak;
    Double volume;
    Double speed;


    public enum ActionType {
        Personal,
        Group,
        Reaction
    }

    ActionType actionType;

    Action(ActionType actionType, int number) {
        this.actionType = actionType;
        String category;
        category = "";
        int lengthOver = 0;
        switch (actionType) {
            case Personal:
                category = "personal_topic";
                if (personalTopicLength <= number) lengthOver = 1;
                break;
            case Group:
                category = "group_topic";
                if (groupTopicLength <= number) lengthOver = 1;
                break;
            case Reaction:
                category = "reaction";
                if (groupTopicLength <= number) lengthOver = 1;
                break;
        }

        if (lengthOver == 0) {
            emotion = root.get(category).get(number).get("emotion").asText();
            minDuration = root.get(category).get(number).get("min_duration").asInt();
            maxDuration = root.get(category).get(number).get("max_duration").asInt();
            soundEffect = root.get(category).get(number).get("sound_effect").asText();
            type = root.get(category).get(number).get("type").asText();
            text = root.get(category).get(number).get("params").get("text").asText();
            speak = root.get(category).get(number).get("params").get("speak").asText();
            volume = root.get(category).get(number).get("params").get("volume").asDouble();
            speed = root.get(category).get(number).get("params").get("speed").asDouble();
        } else {
            System.out.println("存在しない番号の" + category + "です");
        }
    }



    public static void InitRoot(Context context_input){
        try {
            context = context_input;
            AssetManager as = context.getAssets();
            mapper = new ObjectMapper();
            root = mapper.readTree(as.open("Actions.json"));

        }catch ( IOException e) {
            System.out.println("InitRoot_exception");
        }

        groupTopicLength = root.get("group_topic").size();
        personalTopicLength = root.get("personal_topic").size();
        reactionLength = root.get("reaction").size();

        System.out.println(groupTopicLength);
        System.out.println(personalTopicLength);
        System.out.println(reactionLength);
    }

        //Actionの値を読み込み
/*
    public void ChangeAction(ActionType actionType) {
        String category;
        category = "";

        switch(actionType){
            case Personal:
                category = "PersonalTopic";
                break;
            case Group:
                category = "GroupTopic";
                break;
            case Reaction:
                category = "reaction";
                break;
        }

        Random rnd = new Random();
        int read = rnd.nextInt(root.get(category+"_num").asInt());
        emotion = root.get(category).get(read).get("emotion").asText();
        min_duration = root.get(category).get(read).get("min_duration").asInt();
        max_duration = root.get(category).get(read).get("max_duration").asInt();
        sound_effect = root.get(category).get(read).get("sound_effect").asText();
        type = root.get(category).get(read).get("type").asText();
        text = root.get(category).get(read).get("params").get("text").asText();
        speak = root.get(category).get(read).get("params").get("speak").asText();
        volume = root.get(category).get(read).get("params").get("volume").asDouble();
        speed = root.get(category).get(read).get("params").get("speed").asDouble();
    }
    */
}
