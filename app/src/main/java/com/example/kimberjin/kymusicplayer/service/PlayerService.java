package com.example.kimberjin.kymusicplayer.service;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import com.example.kimberjin.kymusicplayer.application.GlobalVal;
import com.example.kimberjin.kymusicplayer.bean.Music;
import com.example.kimberjin.kymusicplayer.util.GeneralUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

// https://www.cnblogs.com/io1024/p/11568507.html

public class PlayerService extends Service implements MediaPlayer.OnCompletionListener {

    public static final String TAG = "PlayerService";
    private MediaPlayer mediaPlayer = new MediaPlayer();

    private int playing_progress = 0;
    private int music_position = 0;

    private List<Music> musicList = new ArrayList<>();

    public PlayerService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return new MusicBinder();
    }

    @Override
    public void onCompletion(MediaPlayer mediaPlayer) {

    }

    public void test() {
        Log.e(TAG, "Why always error?");
    }

    public void play(List<Music> list, int position) {
        music_position = position;
        playing_progress = 0;
        musicList = list;
        play(list.get(position));
    }


    public void play(Music music) {
        mediaPlayer.reset();
        try {
            mediaPlayer.setDataSource(music.getUrl());
            mediaPlayer.prepare();
            // register the listener
            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mediaPlayer) {
                    mediaPlayer.start();
                    if (playing_progress > 0) {
                        mediaPlayer.seekTo(playing_progress);
                    }
                }
            });
            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    complete();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void scanLocalMusic() {
        musicList = GeneralUtil.getLocalMusics();
        if (musicList != null) {
            GlobalVal.setLocalMusicList(musicList);
            Log.i(TAG, "Get Local Musics Done!");
        } else {
            Log.i(TAG, "There is no local musics");
        }
    }

    public void playOrStop() {
        if (mediaPlayer.isPlaying()){
            stop();
        }else {
            //onPlay();
        }
    }

    private void stop() {

    }

    private void playNext() {

    }

    private void playPrev() {

    }

    private void complete() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
        }
    }

    public class MusicBinder extends Binder implements IPlayerService {

        public PlayerService getService() {
            return PlayerService.this;
        }

        @Override
        public void onPlay(List<Music> musicList, int position) {
            play(musicList.get(position));
        }

        @Override
        public void onStop() {
            stop();
        }

        @Override
        public void onPlayNext() {
            playNext();
        }

        @Override
        public void onPlayPrev() {
            playPrev();
        }
    }
}
