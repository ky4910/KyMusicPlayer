package com.example.kimberjin.kymusicplayer.util;

import com.example.kimberjin.kymusicplayer.service.PlayerServiceConnection;

/**
 * Created by ky4910 on 2019/10/9
 */
public class AppVal {

    private static AppVal mAppVal;

    public AppVal() {}

    private static AppVal getInstance() {
        if (mAppVal == null) {
            mAppVal = new AppVal();
        }
        return mAppVal;
    }

    PlayerServiceConnection myConn = new PlayerServiceConnection();

}
