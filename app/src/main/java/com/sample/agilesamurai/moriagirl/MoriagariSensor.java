package com.sample.agilesamurai.moriagirl;

import android.media.AudioFormat;
import android.media.AudioRecord;
import android.media.MediaRecorder;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by motoki on 2016/11/03.
 */
public class MoriagariSensor  implements Runnable {
    private static final int SAMPLE_RATE = 8000;

    private int bufferSize;
    private AudioRecord audioRecord;
    private boolean isRecording;
    private boolean isPausing;
    private double baseValue;

    public List soundLevelList = new ArrayList<Double>();

    public interface SoundLevelMeterListener {
        void onMeasure(double db);
    }

    private SoundLevelMeterListener listener;

    public MoriagariSensor() {
        bufferSize = AudioRecord.getMinBufferSize(SAMPLE_RATE,
                AudioFormat.,
                AudioFormat.ENCODING_PCM_16BIT);
        audioRecord = new AudioRecord(MediaRecorder.AudioSource.MIC,
                SAMPLE_RATE, AudioFormat.CHANNEL_CONFIGURATION_MONO,
                AudioFormat.ENCODING_PCM_16BIT, bufferSize);
        listener = null;
        isRecording = true;
        baseValue = 12.0;
        isPausing = false;
    }

    public void setListener(SoundLevelMeterListener l) {
        listener = l;
    }

    @Override
    public void run() {
        android.os.Process
                .setThreadPriority(android.os.Process.THREAD_PRIORITY_URGENT_AUDIO);
        resume();

        short[] buffer = new short[bufferSize];
        while (!isPausing) {
            int read = audioRecord.read(buffer, 0, bufferSize);
            if (read < 0) {
                //throw new IllegalStateException();
            }

            int maxValue = 0;
            for (int i = 0; i < read; i++) {
                maxValue = Math.max(maxValue, buffer[i]);
            }

            double db = 20.0 * Math.log10(maxValue / baseValue);
            Log.d("SoundLevelMeter", "dB:" + db);

            if (listener != null) {
                listener.onMeasure(db);
            }

            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        audioRecord.stop();
        audioRecord.release();
    }

    public void stop() {
        isRecording = false;
    }

    public void pause() {
        if (!isPausing)
            audioRecord.stop();
        isPausing = true;
    }

    public void resume() {
        if (isPausing)
            audioRecord.startRecording();
        isPausing = false;
    }

    public void setBaseValue(double value) {
        baseValue = value;
    }
}
