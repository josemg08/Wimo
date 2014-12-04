package com.wheelsinmotion.jose.wimo.util;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.wheelsinmotion.jose.wimo.activity.FragmentBadges;
import com.wheelsinmotion.jose.wimo.activity.FragmentBeneficts;
import com.wheelsinmotion.jose.wimo.activity.FragmentHome;

/**
 * Created by jose on 11/29/14.
 */
public class TabPagerAdapter extends FragmentPagerAdapter {

    public TabPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int index) {

        switch (index) {
            case 0:
                // Top Rated fragment activity
                return new FragmentHome();
            case 1:
                // Games fragment activity
                return new FragmentBadges();
            case 2:
                // Movies fragment activity
                return new FragmentBeneficts();
        }

        return null;
    }

    @Override
    public int getCount() {
        // get item count - equal to number of tabs
        return 3;
    }

}
