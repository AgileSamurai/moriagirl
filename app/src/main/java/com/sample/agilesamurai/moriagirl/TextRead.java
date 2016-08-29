package com.sample.agilesamurai.moriagirl;

import android.content.Context;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Random;

/**
 * Created by takahashitomokatsu on 2016/08/29.
 */
public class TextRead {
    InputStream inputstream;
    BufferedReader bufferedreader;
    String text;
    String filename;
    Context context;
    //Talking talking;
    int text_num;

    //コンストラクタ
    TextRead(Context context_input,String name) {
        inputstream = null;
        bufferedreader= null;
        text = "";
        context = context_input;
        //talking = new Talking(context);
        filename = name;
        setFile();
    }

    //扱うテキストファイルの名前を設定,行数を取得
    public void setFile() {
        try {

            inputstream = context.getAssets().open(filename);
            bufferedreader = new BufferedReader(new InputStreamReader(inputstream));

            String str;
            while ((str = bufferedreader.readLine()) != null) {
                text_num++;
            }

        } catch (Exception e) {
            // エラー発生時の処理
        }

    }

    //テキストファイルからランダムに読み込み
    public String randomTextRead(){
        try {
            try {
                // assetsフォルダ内のtopic.txt をオープンする
                bufferedreader = new BufferedReader(new InputStreamReader(inputstream));

                //1から10までrandom
                Random rnd = new Random();
                int ran = rnd.nextInt(text_num);

                // １行ずつ読み込む
                String str;
                int count = 0;
                while ((str = bufferedreader.readLine()) != null) {
                    count++;
                    if(count == ran) {
                        text = str;
                        break;
                    }
                }

                //話す
               // talking.talk(text);


            } finally {
                //ファイルを閉じる
                if (inputstream != null) inputstream.close();
                if (bufferedreader != null) bufferedreader.close();
            }
        } catch (Exception e) {
            // エラー発生時の処理
        }
        return text;
    }

    //テキストファイルの指定した行を読み込み,関数と引数のの名前が悪い
    public String simpleTextRead(int index){
        try {
            try {
                bufferedreader = new BufferedReader(new InputStreamReader(inputstream));

                //1から10までrandom

                // １行ずつ読み込む
                String str;
                int count = 0;
                while ((str = bufferedreader.readLine()) != null) {
                    count++;
                    if(count == index) {
                        text = str;
                        break;
                    }
                }

                //話す
                //talking.talk(text);

            } finally {
                //ファイルを閉じる
                if (inputstream != null) inputstream.close();
                if (bufferedreader != null) bufferedreader.close();
            }
        } catch (Exception e) {
            // エラー発生時の処理
        }

        return text;
    }

}
