package com.sample.agilesamurai.moriagirl.views;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Pair;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.sample.agilesamurai.moriagirl.App;
import com.sample.agilesamurai.moriagirl.R;
import com.sample.agilesamurai.moriagirl.models.SoundMeterDataModel;
import com.sample.agilesamurai.moriagirl.models.SoundMeterModel;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by ibara on 2016/11/13.
 */

public class SoundMeterActivity extends Activity {
    private LineChart chart;
    private LineData lineData;
    private SoundMeterDataModel soundMeterDataModel;

    private CompositeSubscription subscriptions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sound_meter);

        subscriptions = new CompositeSubscription();
        // TODO: Implement ViewModel
        App app = (App) getApplication();
        soundMeterDataModel = app.getSoundMeterDataModel();

        initChart();

        chart.setData(lineData);
        subscriptions.add(soundMeterDataModel.getOutStream()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Pair<Double, Double>>() {
                    @Override
                    public void call(Pair<Double, Double> loudnessAtTime) {
                        lineData.notifyDataChanged();
                        chart.notifyDataSetChanged();
                        chart.invalidate();
                    }
                }));
    }


    @Override
    protected void onDestroy() {
        subscriptions.unsubscribe();
        super.onDestroy();
    }

    private void initChart() {
        chart = (LineChart) findViewById(R.id.chart);
        chart.setDescription(null); // hide description
        chart.getAxisRight().setDrawLabels(false);
        chart.setTouchEnabled(false);
        chart.setDragEnabled(false);
        chart.setScaleEnabled(false);
        chart.setDrawGridBackground(false);

        chart.setPinchZoom(false);

        chart.setBackgroundColor(Color.LTGRAY);

        chart.getLegend().setEnabled(false);

        XAxis xaxis = chart.getXAxis();
        xaxis.setTextColor(Color.BLACK);

        YAxis laxis = chart.getAxisLeft();
        laxis.setTextColor(Color.BLACK);
        laxis.setDrawGridLines(true);
        laxis.setDrawZeroLine(true);

        laxis.setAxisMaximum(SoundMeterModel.LOUDNESS_MAX_VALUE);
        laxis.setAxisMinimum(SoundMeterModel.LOUDNESS_MIN_VALUE);


        // Get sound data from SoundMeterDataModel and set it to chart
        LineDataSet dataset = soundMeterDataModel.getDataset();
        dataset.setMode(LineDataSet.Mode.CUBIC_BEZIER);
        dataset.setDrawFilled(true);
        dataset.setFillColor(Color.GREEN);
        dataset.setDrawValues(false);
        dataset.setColor(Color.GREEN);
        dataset.setDrawCircles(false);
        lineData = new LineData(dataset);
        chart.setData(lineData);
    }
}