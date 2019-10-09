package com.example.kimberjin.kymusicplayer.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.kimberjin.kymusicplayer.service.PlayerService;
import com.example.kimberjin.kymusicplayer.service.PlayerServiceConnection;

/**
 * Created by ky4910 on 2019/10/9
 */
public class SplashActivity extends AppCompatActivity {

    PlayerServiceConnection playerConn = new PlayerServiceConnection();

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
        initService();
        initDatabase();
    }

    private void initView() {
        // do nothing
    }

    private void initService() {
        Intent intent = new Intent(this, PlayerService.class);
        startService(intent);
        bindService(intent, playerConn.getConnection(), BIND_AUTO_CREATE);
    }

    private void initDatabase() {

    }
}

