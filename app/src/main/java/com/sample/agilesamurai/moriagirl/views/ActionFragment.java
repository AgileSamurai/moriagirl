package com.sample.agilesamurai.moriagirl.views;

import android.databinding.DataBindingUtil;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sample.agilesamurai.moriagirl.R;
import com.sample.agilesamurai.moriagirl.databinding.FragmentActionBinding;
import com.sample.agilesamurai.moriagirl.utils.Action;
import com.sample.agilesamurai.moriagirl.utils.ReactionAction;

/**
 * A placeholder fragment containing a simple view.
 */
public class ActionFragment extends Fragment {

    public ActionFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentActionBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_action, container, false);
        Action action = new ReactionAction("emotion", 0.0, 1.0, "soundEffect", 0, 1);
        binding.setAction(action);
        return binding.getRoot();
    }
}
