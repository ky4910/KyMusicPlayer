package com.example.kimberjin.kymusicplayer.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kimberjin.kymusicplayer.R;
import com.example.kimberjin.kymusicplayer.adapter.DetailsVpAdapter;
import com.example.kimberjin.kymusicplayer.application.GlobalVal;
import com.example.kimberjin.kymusicplayer.bean.Music;
import com.example.kimberjin.kymusicplayer.ui.AlbumView;
import com.example.kimberjin.kymusicplayer.util.ImageTools;

import java.util.ArrayList;

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

    private ArrayList<View> mViewPagerContent = new ArrayList<View>(2);

    AlbumView albumView;
    TextView tv_singer;
    TextView tv_single_lrc;

    TextView tv_all_lrc;

    ViewPager viewPager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        overridePendingTransition(R.anim.activity_slide_up, R.anim.activity_no_slide);
        bindViews();
        initViewPagerContent();
        initUi();
        DetailsVpAdapter detailsVpAdapter = new DetailsVpAdapter(mViewPagerContent);
        viewPager.setAdapter(detailsVpAdapter);
        setClickListener();
    }

    private void bindViews() {
        img_drop_down = findViewById(R.id.detail_iv_drop_down);
        tv_detail_music_title = findViewById(R.id.detail_tv_music_title);
        tv_detail_music_title.setText(GlobalVal.getPlayingMusic().getTitle());
        play_pre_music = findViewById(R.id.detail_play_pre);
        play_current_music = findViewById(R.id.detail_play_start);
        play_next_music = findViewById(R.id.detail_play_next);

        viewPager = findViewById(R.id.detail_vp_play_container);
    }

    private void initUi() {
        Music music = GlobalVal.getPlayingMusic();
        // init detail activity UI
//        Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.drawable.app_icon_2);
        Bitmap bmp = BitmapFactory.decodeFile(music.getAlbumImgPath());
        albumView.setImage(ImageTools.scaleBitmap(bmp));
        tv_singer.setText(music.getArtist());
        if (GlobalVal.getPlayService().isPlaying()) {
            albumView.start();
            play_current_music.setImageResource(R.drawable.player_btn_pause_normal);
        } else {
            albumView.pause();
            play_current_music.setImageResource(R.drawable.player_btn_play_normal);
        }
    }

    private void initViewPagerContent() {
        View albumVpView = View.inflate(this, R.layout.detail_pager_album, null);
        albumView = albumVpView.findViewById(R.id.detail_album_view);
        tv_singer = albumVpView.findViewById(R.id.detail_tv_singer);
        tv_single_lrc = albumVpView.findViewById(R.id.detail_single_lrc);

        View lrcView = View.inflate(this, R.layout.detail_pager_lrc, null);
        tv_all_lrc = lrcView.findViewById(R.id.detail_tv_all_lrc);

        mViewPagerContent.add(albumVpView);
        mViewPagerContent.add(lrcView);
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
