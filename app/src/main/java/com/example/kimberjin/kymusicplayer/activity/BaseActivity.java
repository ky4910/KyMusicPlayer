package com.example.kimberjin.kymusicplayer.activity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;

import com.example.kimberjin.kymusicplayer.application.GlobalVal;
import com.example.kimberjin.kymusicplayer.service.PlayerService;

/**
 * Created by ky4910 on 2019/10/11
 */
public abstract class BaseActivity extends AppCompatActivity {

    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            "android.permission.READ_EXTERNAL_STORAGE",
            "android.permission.WRITE_EXTERNAL_STORAGE" };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setSystemBarTransparent();
        getAuthentication();
    }

    private void setSystemBarTransparent() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            // LOLLIPOP解决方案
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            // KITKAT解决方案
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }

    public PlayerService getPlayService() {

        PlayerService service = GlobalVal.getPlayService();
        if (service == null) {
            startActivity(new Intent(this, SplashActivity.class));
            GlobalVal.clearAll();
        }

        return service;
    }

    private void getAuthentication() {
        try {
            //检测是否有写的权限
            int permission = ContextCompat.checkSelfPermission(BaseActivity.this,
                    "android.permission.READ_EXTERNAL_STORAGE");
            // Manifest.permission.WRITE_EXTERNAL_STORAGE
            if (permission != PackageManager.PERMISSION_GRANTED) {
                // 没有写的权限，去申请写的权限，会弹出对话框
                ActivityCompat.requestPermissions(BaseActivity.this,
                        PERMISSIONS_STORAGE, REQUEST_EXTERNAL_STORAGE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
