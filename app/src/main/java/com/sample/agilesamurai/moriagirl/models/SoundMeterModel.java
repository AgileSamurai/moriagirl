package com.sample.agilesamurai.moriagirl.models;

import android.media.AudioFormat;
import android.media.AudioRecord;
import android.media.MediaRecorder;
import android.util.Pair;

import com.google.common.primitives.Shorts;

import java.util.Collections;
import java.util.List;

import rx.Observable;
import rx.subjects.PublishSubject;

/**
 * Model of sound meter.
 * This class provide a observable stream, which pushes the value of loudness of each time interval.
 * Created by ibara on 2016/11/02.
 */

public class SoundMeterModel {
    public final static int LOUDNESS_MAX_VALUE = Short.MAX_VALUE;
    public final static int LOUDNESS_MIN_VALUE = 0;

    private static SoundMeterModel singleton = new SoundMeterModel();
    private SoundMeterModelImpl impl;

    private SoundMeterModel() {
        impl = new SoundMeterModelImpl();
    }

    public static SoundMeterModel getInstance() {
        return singleton;
    }

    public void start() {
        impl.start();
    }
    public void stop() {
        impl.stop();
    }

    public Observable<Integer> getSoundLevel() {
        return impl.getSoundLevel();
    }
}

class SoundMeterModelImpl {
    // The best choice may be PCM_8BIT encoding
    // but currently only PCM_16BIT encoding is possible
    // see the source, https://source.codeaurora.org/quic/la/platform/frameworks/base/tree/media/java/android/media/AudioRecord.java?id=refs/heads/jb_mr1 #499
    private final static int SAMPLE_RATE = 8000;
    private final static int BIT_RATE    = AudioFormat.ENCODING_PCM_16BIT;
    private final static int CHANNEL     = AudioFormat.CHANNEL_IN_MONO;
    private final static int BUFFER_SIZE = AudioRecord.getMinBufferSize(SAMPLE_RATE, CHANNEL, BIT_RATE);
    private final static int FRAME_BUFFER_SIZE = BUFFER_SIZE / 2;
    private AudioRecord recorder;
    private short[] buffer;

    PublishSubject<Integer> soundLevel;

    SoundMeterModelImpl() {
        initAudioRecord();
    }

    private void initAudioRecord() {
        // TODO: Maybe can use ByteBuffer instead of short[]
        // AudioRecord would not run if frame size is wrong
        buffer = new short[FRAME_BUFFER_SIZE];
        recorder = new AudioRecord(MediaRecorder.AudioSource.MIC, SAMPLE_RATE, CHANNEL, BIT_RATE, BUFFER_SIZE);
        recorder.setPositionNotificationPeriod(FRAME_BUFFER_SIZE);
        AudioRecord.OnRecordPositionUpdateListener listener = new AudioRecord.OnRecordPositionUpdateListener() {
            @Override
            public void onMarkerReached(AudioRecord recorder) {}  // Don't know the usage of this function
            @Override
            public void onPeriodicNotification(AudioRecord recorder) {
                read();
            }
        };
        recorder.setRecordPositionUpdateListener(listener);
    }

    private void read() {
        final int OFFSET = 0;
        recorder.read(buffer, OFFSET, FRAME_BUFFER_SIZE);
        Integer max = Observable.from(Shorts.asList(buffer))
            .map(Math::abs)
            .toList()
            .map(Collections::max)
            .toBlocking().single();
        soundLevel.onNext(max);
    }

    Observable<Integer> getSoundLevel() {
        return soundLevel;
    }

    void stop() {
        recorder.stop();
        // Release AudioRecord Object
        // after calling, should not restart the object
        recorder.release();
    }

    void start() {
        // TODO: Return exceptions when called twice
        // start() should only be called once over the application
        recorder.startRecording();
        // Anti-human behavior, onPeriodicNotification works after the first read() called
        read();
    }
}
