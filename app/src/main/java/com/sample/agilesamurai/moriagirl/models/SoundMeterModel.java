package com.sample.agilesamurai.moriagirl.models;

import android.media.AudioFormat;
import android.media.AudioRecord;
import android.media.MediaRecorder;
import android.util.Pair;

import com.annimon.stream.Collectors;
import com.annimon.stream.Stream;
import com.google.common.primitives.Shorts;

import java.util.Collections;
import java.util.List;

import rx.Observable;
import rx.subjects.Subject;
import rx.subjects.PublishSubject;

/**
 * Model of sound meter.
 * This class provide a observable stream, which pushes the value of loudness of each time interval.
 * Created by ibara on 2016/11/02.
 */

public class SoundMeterModel {
    public static int LOUDNESS_MAX_VALUE = Short.MAX_VALUE;
    public static int LOUDNESS_MIN_VALUE = 0;

    private SoundMeterModelImpl impl;

    public SoundMeterModel() {
        impl = new SoundMeterModelImpl();
    }

    public void start() {
        impl.start();
    }
    public void stop() {
        impl.stop();
    }

    public Observable<Pair<Double, Double>> getOutStream() {
        return impl.soundStream.asObservable();
    }
}

class SoundMeterModelImpl {
    // The best choice may be PCM_8BIT encoding
    // but currently only PCM_16BIT encoding is possible
    // see the source, https://source.codeaurora.org/quic/la/platform/frameworks/base/tree/media/java/android/media/AudioRecord.java?id=refs/heads/jb_mr1 #499
    final private int SAMPLE_RATE = 8000;
    final private int BIT_RATE    = AudioFormat.ENCODING_PCM_16BIT;
    final private int CHANNEL     = AudioFormat.CHANNEL_IN_MONO;
    final private int BUFFER_SIZE = AudioRecord.getMinBufferSize(SAMPLE_RATE, CHANNEL, BIT_RATE);
    final private int FRAME_BUFFER_SIZE = BUFFER_SIZE / 2;
    private AudioRecord recorder;
    private short[] buffer;

    private Long startTime;

    Subject<Pair<Double, Double>, Pair<Double, Double>> soundStream;

    SoundMeterModelImpl() {
        initAudioRecord();
        soundStream = PublishSubject.create();
    }

    private void initAudioRecord() {
        // TODO: Maybe can use ByteBuffer instead of short[]
        // AudioRecord would not run if frame size is wrong
        buffer = new short[FRAME_BUFFER_SIZE];
        recorder = new AudioRecord(MediaRecorder.AudioSource.MIC, SAMPLE_RATE, CHANNEL, BIT_RATE, BUFFER_SIZE);

        recorder.setRecordPositionUpdateListener(new AudioRecord.OnRecordPositionUpdateListener() {
            @Override
            public void onMarkerReached(AudioRecord recorder) {}  // Don't know the usage of this function
            @Override
            public void onPeriodicNotification(AudioRecord recorder) {
                // TODO: Refactor read method
                read();
            }
        });
        recorder.setPositionNotificationPeriod(FRAME_BUFFER_SIZE);
        // TODO: Should not use one-branch if-statement
        if (recorder.getState() != AudioRecord.STATE_INITIALIZED) {
            soundStream.onError(new Exception("AudioRecord initialization failed"));
        }
    }

    private void read() {
        final int OFFSET = 0;
        recorder.read(buffer, OFFSET, FRAME_BUFFER_SIZE);
        // TODO: Find a better way to find maximum (in Java7)
        List<Integer> list = Stream.of(Shorts.asList(buffer))
                .map(Math::abs)               // Math::abs returns int type (and then auto boxing to Integer)
                .collect(Collectors.toList());
        Integer max = Collections.max(list);  // Not have max() in Lightweight-Stream-API
        Long elapsedTime = System.currentTimeMillis() - startTime;

        Pair<Double, Double> loudnessAtTime = Pair.create(elapsedTime.doubleValue() / 1000, max.doubleValue());
        soundStream.onNext(loudnessAtTime);
    }

    void stop() {
        recorder.stop();
        // Release AudioRecord Object
        // after calling, should not restart the object
        recorder.release();
        soundStream.onCompleted();
    }

    void start() {
        // TODO: Return exceptions when called twice
        // start() should only be called once over the application
        startTime = System.currentTimeMillis();
        recorder.startRecording();
        // Anti-human behavior, onPeriodicNotification only works after the first read() called
        read();
    }
}