package com.wheelsinmotion.jose.wimo.activity;

import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wheelsinmotion.jose.wimo.R;
import com.wheelsinmotion.jose.wimo.model.Badge;
import com.wheelsinmotion.jose.wimo.util.DBHelper;

/**
 * Created by jose on 12/3/14.
 */
public class FragmentBadges extends Fragment {
    DBHelper db;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_badges, container, false);

        db = new DBHelper(rootView.getContext());

        Badge badge = new Badge(1, "Badge Bronce Tree", 10, R.drawable.badge_tree_bronce);

        db.createBadge(badge);
        db.getBadges(11);
        db.closeDB();

        return rootView;
    }
}