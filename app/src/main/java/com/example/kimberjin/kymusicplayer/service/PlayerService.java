package com.example.kimberjin.kymusicplayer.service;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;

import com.example.kimberjin.kymusicplayer.application.GlobalVal;
import com.example.kimberjin.kymusicplayer.bean.Music;
import com.example.kimberjin.kymusicplayer.util.GeneralUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

// https://www.cnblogs.com/io1024/p/11568507.html

public class PlayerService extends Service implements MediaPlayer.OnCompletionListener, IPlayerService {

    public static final String TAG = "PlayerService";
    private MediaPlayer mediaPlayer = new MediaPlayer();

    private int playing_progress = 0;
    private int music_position = 0;

    private List<Music> musicList = new ArrayList<>();
    private OnPlayMusicListener mPlayerServiceListener;

    public PlayerService() {
    }

    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message message) {
            switch (message.what) {
                // update current music progress
                case 1:
                    if (mediaPlayer != null) {
                        playing_progress = mediaPlayer.getCurrentPosition();
                        mPlayerServiceListener.onMusicCurrentPosition(playing_progress);
                        handler.sendEmptyMessageDelayed(1, 50);
                    }
            }
            return false;
        }
    });

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

    public void play(List<Music> list, int position) {
        music_position = position;
//        playing_progress = 0;
        musicList = list;
        play(list.get(position));
        mPlayerServiceListener.onMusicPlay();
    }

    /*
         MediaPlayer音频播放一般过程：初始化MediaPlayer - 加载媒体源 - 准备 - 开始播放
            1. MediaPlayer mediaPlayer = new MediaPlayer();
            2. mediaPlayer.setDataSource("PATH");
            3. mediaPlayer.prepare();
            4. mediaPlayer.start();
    */
    public void play(Music music) {
        mediaPlayer.reset();
        try {
            mediaPlayer.setDataSource(music.getUrl());
            mediaPlayer.prepare();
            mediaPlayer.start();

            // register the listener
            /*
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
            */
            GlobalVal.setIsPlaying(true);
            GlobalVal.setPlayingMusic(music);
            mPlayerServiceListener.onMusicPlay();
            // TODO: write history data to Database
            handler.sendEmptyMessage(1);
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

    public void playOrPause() {
        if (mediaPlayer.isPlaying()){
            pause();
        } else {
            resume();
        }
    }

    private void pause() {
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
            GlobalVal.setIsPlaying(false);
            mPlayerServiceListener.onMusicPause();
        }
    }

    private void resume() {
        if (isPlaying())
            return;
        mediaPlayer.start();
        mPlayerServiceListener.onMusicPlay();
    }

    public boolean isPlaying() {
        // return GlobalVal.getPlayingState();
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            Log.e(TAG, "isPlaying return true! " + (mediaPlayer!=null) + ", " + mediaPlayer.isPlaying());
            return true;
        } else {
            Log.e(TAG, "isPlaying return false!" + (mediaPlayer!=null) + ", " + mediaPlayer.isPlaying());
            return false;
        }
        //return  mediaPlayer != null && mediaPlayer.isPlaying();
    }

    private void complete() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
        }
    }

    public void setOnPlayerListener(OnPlayMusicListener listener) {
        mPlayerServiceListener = listener;
    }

    @Override
    public void onPlay() {
        play(musicList.get(music_position));
    }

    @Override
    public void onPause() {

    }

    @Override
    public void onPlayNext() {
        playing_progress = 0;
        if (musicList == null) {
            musicList = GeneralUtil.getLocalMusics();
        }
        if (music_position == musicList.size() - 1) {
            music_position = 0;
        } else {
            music_position++;
        }
        onPlay();
    }

    @Override
    public void onPlayPrev() {
        playing_progress = 0;
        GlobalVal.setIsPlaying(true);
        if (musicList == null) {
            musicList = GeneralUtil.getLocalMusics();
        }
        if (music_position == 0) {
            music_position = musicList.size() - 1;
        } else {
            music_position--;
        }
        Log.e(TAG, "onPlayPrev before onPlay!");
        onPlay();
        Log.e(TAG, "onPlayPrev after onPlay!");
    }

    public class MusicBinder extends Binder {
        public PlayerService getService() {
            return PlayerService.this;
        }
    }
}
