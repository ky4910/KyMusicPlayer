package com.example.kimberjin.kymusicplayer.application;

import android.app.Application;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import com.example.kimberjin.kymusicplayer.database.DbClient;
import com.example.kimberjin.kymusicplayer.util.GeneralUtil;

/**
 * Created by ky4910 on 2019/10/10
 */
public class MusicApplication extends Application {

    public static Context mContext;

    public static int mScreenWidth;
    public static int mScreenHeight;

    @Override
    public void onCreate() {
        super.onCreate();

        mContext = getApplicationContext();

        GlobalVal.init(this);
        GeneralUtil.init(this);
        DbClient.init(this);

        WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(dm);
        mScreenWidth = dm.widthPixels;
        mScreenHeight = dm.heightPixels;
    }

}
