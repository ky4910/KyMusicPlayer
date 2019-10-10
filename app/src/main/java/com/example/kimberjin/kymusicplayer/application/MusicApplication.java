package com.example.kimberjin.kymusicplayer.application;

import android.app.Application;

/**
 * Created by ky4910 on 2019/10/10
 */
public class MusicApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        GlobalVal.init(this);
    }

}
