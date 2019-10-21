package com.example.kimberjin.kymusicplayer.activity;

import android.content.pm.PackageManager;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.kimberjin.kymusicplayer.R;
import com.example.kimberjin.kymusicplayer.adapter.FragmentAdapter;
import com.example.kimberjin.kymusicplayer.adapter.MyViewPagerAdapter;
import com.example.kimberjin.kymusicplayer.fragment.DetailsFragment;
import com.example.kimberjin.kymusicplayer.fragment.HistoryFragment;
import com.example.kimberjin.kymusicplayer.fragment.LocalFragment;
import com.example.kimberjin.kymusicplayer.fragment.OnlineFragment;
import com.example.kimberjin.kymusicplayer.service.OnPlayMusicListener;
import com.example.kimberjin.kymusicplayer.application.GlobalVal;

/**
 *  Created by ky4910 on 2019/09/27
 */

// 可依音乐播放器

public class MainActivity extends BaseActivity implements OnPlayMusicListener, View.OnClickListener {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    protected MyViewPagerAdapter pagerAdapter;

    TextView tvTitle, tvArtist;
    ImageView imgView;
    ImageButton img_play, img_next;
    SeekBar mSeekBarCurrent;

    private DetailsFragment detailsFragment;

    boolean isShowingFragment = false;
    boolean isShowedFragment = false;

    public static final String TAG = "Main_Activity";

    protected String mTitles[] = {"本地音乐", "网络音乐", "播放历史"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    protected void initView() {
        getPlayService().setOnPlayerListener(this);

        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);

        tvTitle = findViewById(R.id.tv_music_name);
        tvArtist = findViewById(R.id.tv_music_author);
        imgView = findViewById(R.id.img_music_bottom);

        img_play = findViewById(R.id.imgBtn_play);
        img_next = findViewById(R.id.imgBtn_next);
        mSeekBarCurrent = findViewById(R.id.play_progress_seekbar);

        LocalFragment localFragment = new LocalFragment();
        OnlineFragment onlineFragment = new OnlineFragment();
        HistoryFragment historyFragment = new HistoryFragment();

        FragmentAdapter fragmentAdapter = new FragmentAdapter(getSupportFragmentManager());
        fragmentAdapter.addFragment(localFragment);
        fragmentAdapter.addFragment(onlineFragment);
        fragmentAdapter.addFragment(historyFragment);

        pagerAdapter = new MyViewPagerAdapter(getSupportFragmentManager(),
                fragmentAdapter.getFragmentList(), mTitles);
        viewPager.setAdapter(pagerAdapter);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                // 取消平滑切换
                viewPager.setCurrentItem(tab.getPosition(), false);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        tabLayout.setupWithViewPager(viewPager);

        setListener();
    }

    public void setListener() {
        img_play.setOnClickListener(this);
        img_next.setOnClickListener(this);
        imgView.setOnClickListener(this);
        mSeekBarCurrent.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imgBtn_play:
                Log.i(TAG, "play button clicked!");
                getPlayService().playOrStop();
                break;
            case R.id.imgBtn_next:
                Log.i(TAG, "next button clicked!");
                getPlayService().playNext();
                break;
            case R.id.img_music_bottom:
                Log.i(TAG, "music icon clicked!");
                showDetailsFragment();
                break;
        }
    }

    @Override
    public void onMusicPlay() {
        setBottomPlayBar();
        img_play.setImageResource(R.drawable.default_playing);
    }

    @Override
    public void onMusicCurrentPosition(int currentPosition) {
        int tmpValue = currentPosition;
        int progress = tmpValue * 100 / (int)GlobalVal.getPlayingMusic().getDuration();
        mSeekBarCurrent.setProgress(progress);
    }

    @Override
    public void onMusicStop() {
        Log.e(TAG, "onMusicStop function from MainActivity!");
        img_play.setImageResource(R.drawable.default_stop);
    }

    @Override
    public void onMusicComplete() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void setBottomPlayBar() {
        if (GlobalVal.getPlayService() == null)
            return;
        tvTitle.setText(GlobalVal.getPlayingMusic().getTitle());
        tvArtist.setText(GlobalVal.getPlayingMusic().getArtist());
        String imgPath = GlobalVal.getPlayingMusic().getAlbumImgPath();
        Glide.with(this).load(imgPath).placeholder(R.drawable.default_music).into(imgView);
    }

    private void showDetailsFragment() {
        if (isShowingFragment) {
            Log.i(TAG, "Detail Fragment is showing! Return!");
            return;
        }

        Log.i(TAG, "begin show details fragment!");
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.fragment_slide_up, 0);
        if (detailsFragment == null) {
            detailsFragment = new DetailsFragment();
            fragmentTransaction.replace(android.R.id.content, detailsFragment);
        } else {
            fragmentTransaction.show(detailsFragment);
        }
        fragmentTransaction.commitNow();

        isShowingFragment = true;
        isShowedFragment = true;
    }
}
