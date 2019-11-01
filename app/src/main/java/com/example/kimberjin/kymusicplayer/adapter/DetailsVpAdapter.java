package com.example.kimberjin.kymusicplayer.adapter;

import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by ky4910 on 2019/11/1
 */
public class DetailsVpAdapter extends PagerAdapter {

    private ArrayList<View> mViewPagerContent = new ArrayList<View>(2);

    public DetailsVpAdapter(ArrayList<View> mViewPagerContent) {
        this.mViewPagerContent = mViewPagerContent;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }

    @Override
    public int getCount() {
        return mViewPagerContent.size();
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        container.addView(mViewPagerContent.get(position));
        return mViewPagerContent.get(position);
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        super.destroyItem(container, position, object);
        container.removeView((View) object);
    }
}
