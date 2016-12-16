package com.sample.agilesamurai.moriagirl.models;

import android.util.Pair;

import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineDataSet;

import rx.Observable;
import rx.Observer;
import rx.subjects.PublishSubject;
import rx.subjects.Subject;

/**
 * This class stores the data of LineChart.
 * Unfortunately, for efficiency and convenience, we have no choice but to use the containers
 * givens by MPAndroidChart.
 */

public class SoundMeterGraphModel {
    private final int CONTAINER_MAX_SIZE = 100;

    private LineDataSet dataset;

    private Observer<Pair<Double, Double>> inStream;
    private Subject<Pair<Double, Double>, Pair<Double, Double>> outStream;

    public SoundMeterGraphModel() {
        initDataset();
        inStream = new Observer<Pair<Double, Double>>() {
            @Override
            public void onCompleted() {
                // TODO: Implement
            }

            @Override
            public void onError(Throwable e) {
                // TODO: Implement
            }

            @Override
            public void onNext(Pair<Double, Double> loudnessAtTime) {
                updateDataset(loudnessAtTime);
                outStream.onNext(loudnessAtTime);
            }
        };
        outStream = PublishSubject.create();
    }

    public LineDataSet getDataset() {
        return this.dataset;
    }

    public Observer<Pair<Double, Double>> getInStream() {
        return inStream;
    }

    public Observable<Pair<Double, Double>> getOutStream() {
        return outStream.asObservable();
    }

    private void initDataset() {
        dataset = new LineDataSet(null, null);
    }

    private void updateDataset(Pair<Double, Double> loudnessAtTime) {
        if(dataset.getEntryCount() >= CONTAINER_MAX_SIZE) {
            dataset.removeFirst();
        }
        else {
            dataset.addEntry(
                    new Entry(loudnessAtTime.first.floatValue(), loudnessAtTime.second.floatValue()
                    ));
        }
    }
}
