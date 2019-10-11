package com.example.kimberjin.kymusicplayer.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.kimberjin.kymusicplayer.application.GlobalVal;
import com.example.kimberjin.kymusicplayer.service.PlayerService;

/**
 * Created by ky4910 on 2019/10/11
 */
public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLayoutId();
        initView();
    }

    protected abstract int getLayoutId();
    protected abstract void initView();

    public PlayerService getPlayService() {

        PlayerService service = GlobalVal.getPlayService();
        if (service == null) {
            startActivity(new Intent(this, SplashActivity.class));
            GlobalVal.clearAll();
        }

        return service;
    }

}
