package com.example.kimberjin.kymusicplayer.service;

import android.app.Service;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.IBinder;

public class PlayerService extends Service implements MediaPlayer.OnCompletionListener, IPlayerService{

    private MediaPlayer mediaPlayer;

    public PlayerService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCompletion(MediaPlayer mediaPlayer) {

    }

    @Override
    public void onPlay() {

    }

    @Override
    public void stop() {

    }

    @Override
    public void onPlayNext() {

    }

    @Override
    public void onPlayPrev() {

    }
}
