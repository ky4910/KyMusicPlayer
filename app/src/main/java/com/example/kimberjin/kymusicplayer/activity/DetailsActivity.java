package com.example.kimberjin.kymusicplayer.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kimberjin.kymusicplayer.R;
import com.example.kimberjin.kymusicplayer.application.GlobalVal;

/**
 * Created by ky4910 on 2019/10/27 18:38
 */
public class DetailsActivity extends BaseActivity implements View.OnClickListener {

    public static final String TAG = "Detail_Activity";

    ImageView img_drop_down;
    TextView tv_detail_music_title;
    ImageButton play_pre_music;
    ImageButton play_current_music;
    ImageButton play_next_music;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        overridePendingTransition(R.anim.activity_slide_up, R.anim.activity_no_slide);
        bindViews();
        initUi();
        setClickListener();
    }

    private void bindViews() {
        img_drop_down = findViewById(R.id.detail_iv_drop_down);
        tv_detail_music_title = findViewById(R.id.detail_tv_music_title);
        tv_detail_music_title.setText(GlobalVal.getPlayingMusic().getTitle());
        play_pre_music = findViewById(R.id.detail_play_pre);
        play_current_music = findViewById(R.id.detail_play_start);
        play_next_music = findViewById(R.id.detail_play_next);
    }

    private void initUi() {
        // init detail activity UI
    }

    private void setClickListener() {
        img_drop_down.setOnClickListener(this);
        play_pre_music.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.detail_iv_drop_down:
                Log.e(TAG, "detail drop down clicked!");
                finish();
                break;
            case R.id.detail_play_pre:
                Log.e(TAG, "detail play previous clicked!");
                break;
            default:
                break;
        }
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.activity_no_slide, R.anim.activity_slide_down);
    }
}
