package com.sample.agilesamurai.moriagirl;

import android.content.Context;
import java.io.InputStreamReader;
import java.io.InputStream;
import java.io.BufferedReader;
import java.util.Random;


/**
 * Created by ito on 2016/08/26.
 */
public class TopicPutter {
    InputStream is;
    BufferedReader br;
    String text;
    //Talking talking;

    TopicPutter() {
        is = null;
        br = null;
        text = "";
    }

    public String TextContents(){
        return text;
    }


    //とりあえず１行目のお題表示
    public void TextPutter(Context context){
        try {
            //talking = new Talking(context);

            try {
                // assetsフォルダ内の sample.txt をオープンする
                is = context.getAssets().open("sample.txt");
                br = new BufferedReader(new InputStreamReader(is));

                // １行ずつ読み込み、改行を付加する
                String text = br.readLine();

                //話す
                //talking.talk(text);

            } finally {
                if (is != null) is.close();
                if (br != null) br.close();
            }
        } catch (Exception e) {
            // エラー発生時の処理
        }
    }

    //10行の中でランダムで１行表示
    public void RandomTextPutter(Context context){
        try {
            //talking = new Talking(context);

            try {
                // assetsフォルダ内のtopic.txt をオープンする
                is = context.getAssets().open("topic.txt");
                br = new BufferedReader(new InputStreamReader(is));

                //1から10までrandom
                Random rnd = new Random();
                int ran = rnd.nextInt(11) + 1;

                // １行ずつ読み込み、改行を付加する
                String str;
                int count = 0;
                while ((str = br.readLine()) != null) {
                    count++;
                    if(count == ran) {
                        text += str + "\n";
                    }
                }

                //話す
                //talking.talk(text);

            } finally {
                if (is != null) is.close();
                if (br != null) br.close();
            }
        } catch (Exception e) {
            // エラー発生時の処理
        }
    }

}
