package com.example.kimberjin.kymusicplayer.activity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.kimberjin.kymusicplayer.R;
import com.example.kimberjin.kymusicplayer.application.GlobalVal;
import com.example.kimberjin.kymusicplayer.service.PlayerService;
import com.example.kimberjin.kymusicplayer.util.GeneralUtil;

/**
 * Created by ky4910 on 2019/10/9
 */
public class SplashActivity extends AppCompatActivity {

    public static final String TAG = "Splash_Activity";

    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        init();

        /*
            runOnUiThread()这个方法的作用是将当前线程切换到主线程，
            所以产生的作用和Handler传递消息的作用是相同的，但是
            Handler要传递消息再接收消息，不如这个方法简便。
         */
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void init() {
        initView();
        initDatabase();
        GeneralUtil.init(this);
        initService();
    }

    protected void initView() {
        // do nothing
    }

    private void initDatabase() {

    }

    private void initService() {
        Intent intent = new Intent(SplashActivity.this, PlayerService.class);
        startService(intent);
        bindService(intent, playConn, BIND_AUTO_CREATE);
    }

    PlayerServiceConnection playConn = new PlayerServiceConnection();
    public class PlayerServiceConnection implements ServiceConnection {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            PlayerService playerService = ((PlayerService.MusicBinder) iBinder).getService();
            GlobalVal.setPlayService(playerService);
            playerService.scanLocalMusic();
            Log.e(TAG, "Service is connected!");
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            Log.e(TAG, "onServiceDisconnected name: " + componentName);
        }
    }

    @Override
    protected void onDestroy() {
        if (playConn != null) {
            unbindService(playConn);
        }
        super.onDestroy();
    }
}

