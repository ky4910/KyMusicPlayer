package com.example.kimberjin.kymusicplayer.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ky4910 on 2019/9/27
 */
public class MyViewPagerAdapter extends FragmentPagerAdapter {

    private List<Fragment> fragments;
    private String mTitles[];

    public MyViewPagerAdapter(FragmentManager fm, List<Fragment> fragments, String titles[]) {
        super(fm);
        this.fragments = fragments;
        this.mTitles = titles;
    }

    @Override
    public Fragment getItem(int i) {
        return fragments.get(i);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles[position];
    }

    @Override
    public int getCount() {
        return fragments != null ? fragments.size() : 0;
    }
}
