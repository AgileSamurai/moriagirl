package com.sample.agilesamurai.moriagirl.views;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sample.agilesamurai.moriagirl.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class ActionFragment extends Fragment {

    public ActionFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ActionFragmentBinding binding = DataBindingUtil.setContentView(this, R.layout.fragment_action);
        Action action = new
        return inflater.inflate(R.layout.fragment_action, container, false);
    }
}
