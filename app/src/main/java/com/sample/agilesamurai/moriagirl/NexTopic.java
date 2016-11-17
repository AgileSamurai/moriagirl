package com.sample.agilesamurai.moriagirl;

import android.os.AsyncTask;
/**
 * Created by ito on 2016/11/15.
 */

public class NexTopic extends AsyncTask<Integer, Integer, Integer> {

    public NexTopic(){
        super();
    }

    /**
     * バックグランドで行う処理
     */
    @Override
    protected Integer doInBackground(Integer... value) {
        //配列準備

        //現在時刻取得

        //ループ開始
        while(){
            //現在の音量取得

            //時刻の更新
        }

        //結果分析

        //返す値を決める
        return value[0] + 2;
    }

    /**
     * バックグランド処理が完了し、UIスレッドに反映する
     */
    @Override
    protected void onPostExecute(Integer result) {

    }
}
