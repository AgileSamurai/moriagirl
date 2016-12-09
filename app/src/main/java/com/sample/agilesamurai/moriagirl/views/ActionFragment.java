package com.sample.agilesamurai.moriagirl.views;

import android.databinding.DataBindingUtil;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sample.agilesamurai.moriagirl.App;
import com.sample.agilesamurai.moriagirl.R;
import com.sample.agilesamurai.moriagirl.databinding.FragmentActionBinding;
import com.sample.agilesamurai.moriagirl.viewmodels.TopicViewModel;

/**
 * A placeholder fragment containing a simple view.
 */
public class ActionFragment extends Fragment {
    private TopicViewModel viewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentActionBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_action, container, false);

        App app = (App)getActivity().getApplication();
        viewModel = new TopicViewModel(app.getLivelyLevelMeter());
        binding.setViewModel(viewModel);
        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        viewModel.unsubscribe();
    }
}
