package com.example.kimberjin.kymusicplayer.activity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.os.IBinder;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.kimberjin.kymusicplayer.R;
import com.example.kimberjin.kymusicplayer.adapter.FragmentAdapter;
import com.example.kimberjin.kymusicplayer.adapter.MyViewPagerAdapter;
import com.example.kimberjin.kymusicplayer.fragment.HistoryFragment;
import com.example.kimberjin.kymusicplayer.fragment.LocalFragment;
import com.example.kimberjin.kymusicplayer.fragment.OnlineFragment;
import com.example.kimberjin.kymusicplayer.service.OnPlayMusicListener;
import com.example.kimberjin.kymusicplayer.service.PlayerService;
import com.example.kimberjin.kymusicplayer.application.GlobalVal;

/**
 *  Created by ky4910 on 2019/09/27
 */

// 可依音乐播放器

public class MainActivity extends AppCompatActivity implements OnPlayMusicListener {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private MyViewPagerAdapter pagerAdapter;

    public static final String TAG = "Main_Activity";

    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            "android.permission.READ_EXTERNAL_STORAGE",
            "android.permission.WRITE_EXTERNAL_STORAGE" };

    private String mTitles[] = {"本地音乐", "网络音乐", "播放历史"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getAuthentication();
        initService();
        initView();
    }

    private void initView() {

        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);

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
    }

    PlayerServiceConnection playConn = new PlayerServiceConnection();
    public class PlayerServiceConnection implements ServiceConnection {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            PlayerService playerService = ((PlayerService.MusicBinder) iBinder).getService();
            GlobalVal.setPlayService(playerService);
            Log.e(TAG, "Service is connected!");
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            Log.e(TAG, "onServiceDisconnected name: " + componentName);
        }
    }

    private void initService() {
        Intent intent = new Intent(MainActivity.this, PlayerService.class);
        startService(intent);
        bindService(intent, playConn, BIND_AUTO_CREATE);
    }

    @Override
    public void onMusicPlay() {

    }

    @Override
    public void onMusicCurrentPosition(int currentPosition) {

    }

    @Override
    public void onMusicStop() {

    }

    @Override
    public void onMusicComplete() {

    }

    private void getAuthentication() {
        try {
            //检测是否有写的权限
            int permission = ContextCompat.checkSelfPermission(MainActivity.this,
                    "android.permission.READ_EXTERNAL_STORAGE");
            // Manifest.permission.WRITE_EXTERNAL_STORAGE
            if (permission != PackageManager.PERMISSION_GRANTED) {
                // 没有写的权限，去申请写的权限，会弹出对话框
                ActivityCompat.requestPermissions(MainActivity.this,
                        PERMISSIONS_STORAGE, REQUEST_EXTERNAL_STORAGE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
