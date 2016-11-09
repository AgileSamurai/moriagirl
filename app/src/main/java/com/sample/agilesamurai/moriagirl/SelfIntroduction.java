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
    //自己紹介が終わった人数
    int count;
    int size;

    public SelfIntroduction(List<String> name1,int selfintroduction_count,Activity activity){
        talking = new Talking(activity);
        button = (Button)activity.findViewById(R.id.syokai);
        name = name1;
        topicPutter = new TopicPutter(activity);
        this.activity = activity;
        count = selfintroduction_count;
        size = name.size();
    }

    public void introduction() {
        if(count != name.size()){
            talking.talk("では、" + name.get(count) + " さん自己紹介してください～");
            System.out.println(count);
        }else {
            MainActivity.setState(MainActivity.State.TopicPut);
            button.setVisibility(View.INVISIBLE);
            talking.talk("自己紹介終わり!お題を言うね");

        }
    }
}

