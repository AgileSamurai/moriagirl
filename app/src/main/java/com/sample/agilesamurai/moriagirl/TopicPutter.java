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

    TopicPutter() {
        is = null;
        br = null;
        text = "";
    }

    public String TextContents(){
        return text;
    }

    public void TextPutter(Context context){
        try {
            try {
                // assetsフォルダ内の sample.txt をオープンする
                is = context.getAssets().open("sample.txt");
                br = new BufferedReader(new InputStreamReader(is));

                // １行ずつ読み込み、改行を付加する
                String str;
                while ((str = br.readLine()) != null) {
                    text += str + "\n";
                }
            } finally {
                if (is != null) is.close();
                if (br != null) br.close();
            }
        } catch (Exception e) {
            // エラー発生時の処理
        }
    }

    public void RandomTextPutter(Context context){
        try {
            try {
                // assetsフォルダ内の sample.txt をオープンする
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
                    if(count == ran)
                    text += str + "\n";
                }
            } finally {
                if (is != null) is.close();
                if (br != null) br.close();
            }
        } catch (Exception e) {
            // エラー発生時の処理
        }
    }

}
