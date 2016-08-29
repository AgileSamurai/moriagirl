package com.sample.agilesamurai.moriagirl;

import android.app.Activity;
import android.content.Context;
import java.io.InputStreamReader;
import java.io.InputStream;
import java.io.BufferedReader;
import java.util.Random;


/**
 * Created by ito on 2016/08/26.
 */
public class TopicPutter {
    InputStream inputstream;
    BufferedReader bufferedreader;
    String text;
    Activity activity;
    Talking talking;

    TopicPutter(Activity context_input) {
        inputstream = null;
        bufferedreader= null;
        text = "";
        activity = context_input;
        talking = new Talking(activity);
    }

    public String textContents(){
        return text;
    }


    //とりあえず１行目のお題表示
    public void textPut(){
        try {
            try {
                // assetsフォルダ内の sample.txt をオープンする
                inputstream = activity.getAssets().open("sample.txt");
                bufferedreader = new BufferedReader(new InputStreamReader(inputstream));

                // １行ずつ読み込み、改行を付加する
                text = bufferedreader.readLine();

                //話す
                talking.talk(text);

            } finally {
                //ファイルを閉じる
                if (inputstream != null) inputstream.close();
                if (bufferedreader != null) bufferedreader.close();
            }
        } catch (Exception e) {
            // エラー発生時の処理
        }
    }

    //10行の中でランダムで１行表示
    public void randomTextPut(){
        try {
            try {
                // assetsフォルダ内のtopic.txt をオープンする
                inputstream = activity.getAssets().open("topic.txt");
                bufferedreader = new BufferedReader(new InputStreamReader(inputstream));

                //1から10までrandom
                Random rnd = new Random();
                int ran = rnd.nextInt(10) + 1;

                // １行ずつ読み込み、改行を付加する
                String str;
                int count = 0;
                while ((str = bufferedreader.readLine()) != null) {
                    count++;
                    if(count == ran) {
                        text = str;
                    }
                }

                //話す
                talking.talk(text);

            } finally {
                //ファイルを閉じる
                if (inputstream != null) inputstream.close();
                if (bufferedreader != null) bufferedreader.close();
            }
        } catch (Exception e) {
            // エラー発生時の処理
        }
    }

}
