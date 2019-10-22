package com.example.kimberjin.kymusicplayer.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;

import com.example.kimberjin.kymusicplayer.R;

/**
 * Created by ky4910 on 2019/10/19 11:19
 */

public class DetailsFragment extends BaseFragment implements View.OnClickListener,
        View.OnTouchListener, SeekBar.OnSeekBarChangeListener{

    public DetailsFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail, container, false);
        // 监听touch事件，重写View.OnTouchListener的OnTouch方法(需return true)。解决fragment点击穿透事件
        view.setOnTouchListener(this);
        return view;
    }

    @Override
    protected void initView(View view) {

    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        view.performClick();
        return true;
    }

}
