package com.wheelsinmotion.jose.wimo.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wheelsinmotion.jose.wimo.R;

/**
 * Created by jose on 12/3/14.
 */
public class FragmentBeneficts extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_beneficts, container, false);

        return rootView;
    }

}