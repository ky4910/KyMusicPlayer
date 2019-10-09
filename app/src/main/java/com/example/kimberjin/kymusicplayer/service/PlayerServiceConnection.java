package com.example.kimberjin.kymusicplayer.service;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;

/**
 * Created by ky4910 on 2019/10/9
 */
public class PlayerServiceConnection implements ServiceConnection{

    private PlayerService.MusicBindImpl musicBind;
    private PlayerServiceConnection myConn = new PlayerServiceConnection();

    @Override
    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        this.musicBind = (PlayerService.MusicBindImpl) iBinder;
    }

    @Override
    public void onServiceDisconnected(ComponentName componentName) {

    }

    public PlayerService.MusicBindImpl getMusicBindImpl() {
        return musicBind;
    }

    public PlayerServiceConnection getConnection() {
        return myConn;
    }

}

