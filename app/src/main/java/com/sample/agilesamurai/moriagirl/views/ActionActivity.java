package com.sample.agilesamurai.moriagirl.views;

import android.databinding.DataBindingUtil;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

import com.sample.agilesamurai.moriagirl.App;
import com.sample.agilesamurai.moriagirl.R;
import com.sample.agilesamurai.moriagirl.SampleGLSurfaceView;
import com.sample.agilesamurai.moriagirl.databinding.ActivityActionBinding;
import com.sample.agilesamurai.moriagirl.viewmodels.TopicViewModel;

import jp.live2d.Live2D;
import jp.live2d.utils.android.FileManager;

public class ActionActivity extends AppCompatActivity {
    private TopicViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        ActivityActionBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_action);

        App app = (App)getApplication();
        viewModel = new TopicViewModel(app.getLivelyLevelMeter(), app.getActionController(), app.getTimer(), app.getSpeaker());
        binding.setViewModel(viewModel);

        FileManager.init(getApplicationContext());
        Live2D.init();

        SampleGLSurfaceView girlView = new SampleGLSurfaceView(this, "haru/motions/haru_m_05.mtn");
        GLSurfaceView glView = (GLSurfaceView) findViewById(R.id.surfaceView);
        glView.setRenderer(girlView.renderer);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        viewModel.unsubscribe();
    }

}
