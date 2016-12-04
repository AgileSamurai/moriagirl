package com.sample.agilesamurai.moriagirl;



import android.widget.Switch;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.Random;

/**
 * Created by takahashitomokatsu on 2016/12/03.
 */
public class Action {
        static ObjectMapper mapper;
        static JsonNode root;

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



    Action(){
        ChangeAction();
    }


    public static void InitRoot(){
        try {
            mapper = new ObjectMapper();
            root = mapper.readTree(new File("Actions.json"));
        }catch ( IOException e) {
        }
    }

        //Actionの値を読み込み

    public void ChangeAction(ActionController.ActionType type) {
        String category;

        switch(type){
            case ActionController.ActionType.Personal:
                category = "PersonalTopic";
                break;
            case ActionController.ActionType.Group:
                category = "GroupTopic";
                break;
            case ActionController.ActionType.Reaction:
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
