package com.example.kimberjin.kymusicplayer.fragment;

import android.view.MotionEvent;
import android.view.View;
import android.widget.SeekBar;

/**
 * Created by ky4910 on 2019/10/19 11:19
 */

public class DetailsFragment extends BaseFragment implements View.OnClickListener,
        View.OnTouchListener, SeekBar.OnSeekBarChangeListener{

    public DetailsFragment() {
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
        return false;
    }

}
