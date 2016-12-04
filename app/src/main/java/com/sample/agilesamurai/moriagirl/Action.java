package com.sample.agilesamurai.moriagirl;



import android.content.Context;
import android.content.res.AssetManager;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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
    static Context context;



    Action(){
        ChangeAction();
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

    public void ChangeAction() {
        Random rnd = new Random();
        int read = rnd.nextInt(root.get("pTopic_num").asInt());
        emotion = root.get("pTopic").get(read).get("emotion").asText();
        min_duration = root.get("pTopic").get(read).get("min_duration").asInt();
        max_duration = root.get("pTopic").get(read).get("max_duration").asInt();
        sound_effect = root.get("pTopic").get(read).get("sound_effect").asText();
        type = root.get("pTopic").get(read).get("type").asText();
        text = root.get("pTopic").get(read).get("params").get("text").asText();
        speak = root.get("pTopic").get(read).get("params").get("speak").asText();
        volume = root.get("pTopic").get(read).get("params").get("volume").asDouble();
        speed = root.get("pTopic").get(read).get("params").get("speed").asDouble();
    }
}
