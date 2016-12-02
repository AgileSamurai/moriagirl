package com.sample.agilesamurai.moriagirl;

import android.os.AsyncTask;
import android.util.Log;
import java.util.ArrayList;

/**
 * Created by ito on 2016/11/15.
 */

public class NexTopic extends AsyncTask<Integer, Integer, Integer> {

    private TopicPutter topicPutter;

    public NexTopic(){
        super(); //?
        this.topicPutter = topicPutter;
    }

    /**
     * バックグランドで行う処理
     */
    @Override
    protected Integer doInBackground(Integer... value) {
        //値の初期化
        boolean not_excite = false;
        ArrayList<Boolean> exarray = new ArrayList<Boolean>();
        int num =0;

        //ループ開始
        while(true){
            try {
                Thread.sleep(1000); //1秒間待機
            } catch (InterruptedException e) {
                //TODO exception
            }

            //配列に盛り上がり T or F を入れる
            exarray.add(not_excite);

            if(exarray.size() > 10){ //判断配列のサイズ設定
                exarray.remove(0); //一番最初の要素消去

                for(int i=0; i < exarray.size(); i++){
                    if(exarray.get(i) == false) num++;
                }
            }

            if(num > 5) break; //盛り上がっていなかったらbreak;
        }

        //返す値を決める
        return value[0];
    }

    /**
     * バックグランド処理が完了し、UIスレッドに反映する
     */
    @Override
    protected void onPostExecute(Integer result) {
        Log.d("別スレッド1", "ちゃんときてるかチェック " + result);
    }
}
