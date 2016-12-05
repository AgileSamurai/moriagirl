package com.sample.agilesamurai.moriagirl;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import java.util.ArrayList;
import android.app.Activity;

/**
 * Created by ito on 2016/11/15.
 */

public class NexTopic extends AsyncTask<Integer, Integer, Integer> {

    private TopicPutter topicPutter;
    private Activity activity;

    public NexTopic(Activity context_input){
        super(); //?
        activity = context_input;
        topicPutter = new TopicPutter(activity);
    }

    /**
     * バックグランドで行う処理
     */
    @Override
    protected Integer doInBackground(Integer... value) {
        //値の初期化
        boolean not_excite = false;
        ArrayList<Integer> exarray = new ArrayList<Integer>();
        int num = 0;
        int sec = 0;

        //ループ開始
        while(true){
            try {
                Thread.sleep(1000); //1秒間待機
            } catch (InterruptedException e) {
                //TODO exception
            }

            //配列に盛り上がり(Integer)を入れる
            exarray.add(1);

            if(exarray.size() > 3){ //判断配列のサイズ設定
                exarray.remove(0); //一番最初の要素消去

                if((exarray.get(0) >= exarray.get(1)) && (exarray.get(1) >= exarray.get(2))) not_excite = true;
            }

            sec++;

            if((not_excite && sec > value[0]) || sec > value[1]) break; //break;
        }

        //返す値を決める
        return 1;
    }

    /**
     * バックグランド処理が完了し、UIスレッドに反映する
     * ここにリアクションもしくは次のお題を書く
     */
    @Override
    protected void onPostExecute(Integer result) {
        topicPutter.sensorTextPut();
        //Log.d("別スレッド1", "ちゃんときてるかチェック " + result);
    }
}
