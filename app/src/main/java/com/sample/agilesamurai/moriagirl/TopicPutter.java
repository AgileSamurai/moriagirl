package com.sample.agilesamurai.moriagirl;

import android.content.Context;
import java.io.InputStreamReader;
import java.io.InputStream;
import java.io.BufferedReader;


/**
 * Created by ito on 2016/08/26.
 */
public class TopicPutter {
    InputStream is = null;
    BufferedReader br = null;
    String text = "";

    public String TextContents(){
        return text;
    }

    public TopicPutter(Context context) {
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
}
