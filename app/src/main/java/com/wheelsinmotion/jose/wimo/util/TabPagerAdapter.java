package com.wheelsinmotion.jose.wimo.util;

import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by jose on 11/29/14.
 */
public class TabPagerAdapter extends FragmentPagerAdapter implements ActionBar.TabListener, ViewPager.OnPageChangeListener{
    private final Context mContext;
    private final ActionBar mActionBar;
    private final ViewPager mViewPager;
    private final ArrayList<TabInfo> mTabs = new ArrayList<TabInfo>();
    private final String TAG = "";

    static final class TabInfo{
        private final Class<?> clss;
        private final Bundle args;

        TabInfo(Class<?> _class, Bundle _args){
            clss = _class;
            args = _args;
        }
    }


    public TabPagerAdapter(FragmentActivity activity, ViewPager pager) {
        super(activity.getSupportFragmentManager());
        mContext = activity;
        mActionBar = activity.getActionBar();
        mViewPager = pager;
        mViewPager.setAdapter(this);
        mViewPager.setOnPageChangeListener(this);
    }

    public void addTab(ActionBar.Tab tab, Class<?> clss, Bundle args){
        TabInfo info = new TabInfo(clss, args);
        tab.setTag(info);
        tab.setTabListener(this);
        mTabs.add(info);
        mActionBar.addTab(tab);
        notifyDataSetChanged();
    }


    @Override
    public void onPageScrollStateChanged(int state) {

    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }


    @Override
    public void onPageSelected(int position) {
        mActionBar.setSelectedNavigationItem(position);

    }


    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {

    }


    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
        mViewPager.setCurrentItem(tab.getPosition());
        Log.v(TAG, "clicked");
        Object tag = tab.getTag();
        for (int i = 0; i<mTabs.size(); i++){
            if (mTabs.get(i) == tag){
                mViewPager.setCurrentItem(i);
            }
        }
    }


    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {

    }


    @Override
    public Fragment getItem(int position) {
        TabInfo info = mTabs.get(position);
        return Fragment.instantiate(mContext, info.clss.getName(), info.args);
    }


    @Override
    public int getCount() {
        return mTabs.size();
    }

}

    /*public TabPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int index) {

        switch (index) {
            case 0:
                return new FragmentHome();
            case 1:
                return new FragmentBadges();
            case 2:
                return new FragmentBeneficts();
        }

        return null;
    }

    @Override
    public int getCount() {
        // get item count - equal to number of tabs
        return 3;
    }*/