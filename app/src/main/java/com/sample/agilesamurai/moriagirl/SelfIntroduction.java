package com.sample.agilesamurai.moriagirl;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import java.util.List;

/**
 * Created by コウ on 2016/8/29.
 */
public class SelfIntroduction{
    Talking talking;
    Button button;
    List<String> name;
    Activity activity;
    TopicPutter topicPutter;

    public SelfIntroduction(List<String> name1,Activity activity){
        talking = new Talking(activity);
        button = (Button)activity.findViewById(R.id.syokai);
        name = name1;
        topicPutter = new TopicPutter(activity);
        this.activity = activity;
    }

    public void introduction() {
        if(!name.isEmpty()){
            talking.talk("では、" + name.get(0) + " さん自己紹介してください～");
            name.remove(0);
        }else {
            MainActivity.setState(MainActivity.State.TopicPut);
            button.setVisibility(View.INVISIBLE);
            talking.talk("お題を言うね");
        }
    }
}

