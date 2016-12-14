package com.sample.agilesamurai.moriagirl.utils;

import com.sample.agilesamurai.moriagirl.utils.Action;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by ibara on 2016/12/09.
 */

public class ActionParser {
    public static List<? extends Action> parse(String jsonString) throws JSONException {
        JSONObject reader = new JSONObject(jsonString);
    }
}
