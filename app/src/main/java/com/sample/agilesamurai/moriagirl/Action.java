package com.sample.agilesamurai.moriagirl;

import android.content.Context;
import android.content.res.AssetManager;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.Random;

/**
 * Created by takahashitomokatsu on 2016/12/03.
 */
public class Action {
    private static ObjectMapper mapper;
    private static JsonNode root;

    String emotion;
    int min_duration;
    int max_duration;
    String sound_effect;
    String type;
    //params
    String text;
    String speak;
    Double volume;
    Double speed;
    static Context context;

    public enum ActionType{
        Personal,
        Group,
        Reaction
    }

    Action(ActionType type){
        ChangeAction(type);
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
    }

        //Actionの値を読み込み

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
}
