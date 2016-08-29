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
    Balloon balloon;
    Button button;
    List<String> name;
    Activity activity;

    public SelfIntroduction(List<String> name1,Activity activity){
        balloon = new Balloon(activity);
        button = (Button)activity.findViewById(R.id.syokai);
        name = name1;
        this.activity = activity;
    }

    public void introduction() {
        if(!name.isEmpty()){
            balloon.show("では、" + name.get(0) + " さん自己紹介してください～");
            name.remove(0);
        }else {
            button.setVisibility(View.INVISIBLE);
            balloon.show( activity.getString(R.string.byebye));
        }
    }
}

