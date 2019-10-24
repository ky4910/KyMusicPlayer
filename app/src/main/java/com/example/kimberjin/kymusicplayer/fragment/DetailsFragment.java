package com.example.kimberjin.kymusicplayer.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.kimberjin.kymusicplayer.R;
import com.example.kimberjin.kymusicplayer.activity.MainActivity;
import com.example.kimberjin.kymusicplayer.application.GlobalVal;
import com.example.kimberjin.kymusicplayer.bean.Music;
import com.example.kimberjin.kymusicplayer.service.PlayerService;

/**
 * Created by ky4910 on 2019/10/19 11:19
 */

public class DetailsFragment extends BaseFragment implements View.OnClickListener,
        View.OnTouchListener, SeekBar.OnSeekBarChangeListener{

    public static final String TAG = "Detail_Fragment";

    private RelativeLayout rl_top;
    private TextView tvName, tvAuthor;
    private SeekBar sbProgress;
    private TextView tvCurrent, tvDuration;
    private ImageView imgDown, imgPre, imgPlay, imgNext;

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
        imgDown = view.findViewById(R.id.detail_img_down);
        tvName = view.findViewById(R.id.detail_tv_music_name);
        tvAuthor = view.findViewById(R.id.detail_tv_music_author);
        sbProgress = view.findViewById(R.id.detail_bottom_seekBar);
        tvCurrent = view.findViewById(R.id.detail_tv_time_current);
        tvDuration = view.findViewById(R.id.detail_tv_duration);
        imgPre = view.findViewById(R.id.dg_bottom_prev);
        imgPlay = view.findViewById(R.id.dg_bottom_play);
        imgNext = view.findViewById(R.id.dg_bottom_next);
        setDetailListener();
    }

    private void setDetailListener() {
        imgDown.setOnClickListener(this);
        sbProgress.setOnSeekBarChangeListener(this);
        imgPre.setOnClickListener(this);
        imgPlay.setOnClickListener(this);
        imgNext.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.detail_img_down:
                mActivity.onBackPressed();
                break;
            case R.id.dg_bottom_prev:
                getPlayerService().onPlayPrev();
            case R.id.dg_bottom_play:
                getPlayerService().onPlay();
            case R.id.dg_bottom_next:
                getPlayerService().onPlayNext();
        }
    }

    public void updateInfo() {
        Music info = GlobalVal.getPlayingMusic();
        tvName.setText(info.getTitle());
        tvAuthor.setText(info.getArtist());
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
