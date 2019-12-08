package com.example.kimberjin.kymusicplayer.service;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.SystemClock;
import android.util.Log;

import com.example.kimberjin.kymusicplayer.application.GlobalVal;
import com.example.kimberjin.kymusicplayer.bean.Music;
import com.example.kimberjin.kymusicplayer.bean.OnlineSong;
import com.example.kimberjin.kymusicplayer.database.DbClient;
import com.example.kimberjin.kymusicplayer.http.HttpHelper;
import com.example.kimberjin.kymusicplayer.util.GeneralUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

// https://www.cnblogs.com/io1024/p/11568507.html

public class PlayerService extends Service implements MediaPlayer.OnCompletionListener, IPlayerService {

    public static final String TAG = "PlayerService";
    private MediaPlayer mediaPlayer = new MediaPlayer();

    private int playing_progress = 0;
    private int music_position = 0;

    private List<Music> musicList = new ArrayList<>();
    private OnPlayMusicListener mPlayerServiceListener;
    private List<OnPlayMusicListener> listenerList = new ArrayList<>();
    private ExecutorService mProgressUpdatedListener = Executors.newSingleThreadExecutor();

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
        mProgressUpdatedListener.execute(mPublishProgressRunnable);
        mediaPlayer.setOnCompletionListener(this);
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return new MusicBinder();
    }

    @Override
    public void onCompletion(MediaPlayer mediaPlayer) {
        onPlayNext();
    }

    public void play(final List<Music> list, final int position) {
        music_position = position;
//        playing_progress = 0;
        musicList = list;
        Music music = list.get(position);
        if (music.getUrl().equals("none") || GlobalVal.isPlayingOnline()) {
            HttpHelper.getRequestInstance().getMusicLink(music.getId() + "",
                    "baidu.ting.song.play").subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<OnlineSong>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onNext(OnlineSong onlineSong) {
                            list.get(position).setUrl(onlineSong.getSongBitrate().getFile_link());
                            play(list.get(position));
                        }

                        @Override
                        public void onError(Throwable e) {

                        }

                        @Override
                        public void onComplete() {

                        }
                    });
        } else {
            play(list.get(position));
        }
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

            GlobalVal.setIsPlaying(true);
            GlobalVal.setPlayingMusic(music);
            mPlayerServiceListener.onMusicPlay();
            DbClient.addMusic(music);
            handler.sendEmptyMessage(1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void seek(int msec) {
        if (!isPlaying())
            return;
        mediaPlayer.seekTo(msec);
    }

    public void scanLocalMusic() {
        musicList = GeneralUtil.getLocalMusics();
        if (musicList != null) {
            GlobalVal.setLocalMusicList(musicList);
            Log.i(TAG, "Get Local Musics Done!");
        } else {
            Log.i(TAG, "There is no local musics!");
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
        return  mediaPlayer != null && mediaPlayer.isPlaying();
    }

    public void setOnPlayerListener(OnPlayMusicListener listener) {
        mPlayerServiceListener = listener;
        listenerList.add(listener);
    }

    public void clearListener(Object object) {
        for (int i = 0; i < listenerList.size(); i++) {
            if (listenerList.get(i).equals(object)) {
                listenerList.remove(i);
                Log.e(TAG, "clear the listener!");
            }
        }
    }

    @Override
    public void onPlay() {
        // play(musicList.get(music_position));
        play(musicList, music_position);
    }

    @Override
    public void onPause() {

    }

    @Override
    public void onPlayNext() {
        playing_progress = 0;
        musicList = GlobalVal.getPlayingList();
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
        musicList = GlobalVal.getPlayingList();
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



    /**
     * 更新进度的线程
     */
    private Runnable mPublishProgressRunnable = new Runnable() {
        @Override
        public void run() {
            for(;;) {
                if(mediaPlayer != null && mediaPlayer.isPlaying() &&
                        mPlayerServiceListener != null) {
                    mPlayerServiceListener.onMusicCurrentProgress(mediaPlayer.getCurrentPosition());
                }

                SystemClock.sleep(200);
            }
        }
    };

    /**
     * 服务销毁时，释放各种控件
     */
    private void release() {
        if (!mProgressUpdatedListener.isShutdown())
            mProgressUpdatedListener.shutdownNow();
        mProgressUpdatedListener = null;

        if ( mediaPlayer != null)
            mediaPlayer.release();
        mediaPlayer = null;
    }

    @Override
    public void onDestroy() {
        release();
        super.onDestroy();
    }

    public class MusicBinder extends Binder {
        public PlayerService getService() {
            return PlayerService.this;
        }
    }
}
