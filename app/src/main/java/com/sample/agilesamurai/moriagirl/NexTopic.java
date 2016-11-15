package com.sample.agilesamurai.moriagirl;

import android.os.AsyncTask;
/**
 * Created by ito on 2016/11/15.
 */

public class NexTopic extends AsyncTask<Integer, Integer, Integer> {
    public NexTopic(){
        super();
        //
    }

    /**
     * バックグランドで行う処理
     */
    @Override
    protected Integer doInBackground(Integer... value) {
        try {

        } catch (InterruptedException e) {
        }
        return value[0] + 2;
    }

    /**
     * バックグランド処理が完了し、UIスレッドに反映する
     */
    @Override
    protected void onPostExecute(Integer result) {

    }
}
