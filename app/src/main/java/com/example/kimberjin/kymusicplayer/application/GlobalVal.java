package com.example.kimberjin.kymusicplayer.application;

import android.app.Application;
import android.content.Context;

import com.example.kimberjin.kymusicplayer.bean.Music;
import com.example.kimberjin.kymusicplayer.service.PlayerService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ky4910 on 2019/10/10
 */
public class GlobalVal {

    private static GlobalVal mGlobalVal;
    private static Context mContext;
    private PlayerService mPlayerService;
    private PlayerService.MusicBinder mBinder;
    private static Music mPlayingMusic;
    private List<Music> localMusicList = new ArrayList<>();
    private static boolean isPlaying = false;

    private GlobalVal() {}

    private static GlobalVal getInstance() {
        if (mGlobalVal == null) {
            mGlobalVal = new GlobalVal();
        }
        return mGlobalVal;
    }

    public static void init(Application application) {
        getInstance().initialize(application);
    }

    private void initialize(Application application) {
        mContext = application.getApplicationContext();
    }

    public static void setPlayService(PlayerService service) {
        getInstance().mPlayerService = service;
    }

    public static PlayerService getPlayService() {
        return getInstance().mPlayerService;
    }

    public static void setIsPlaying(boolean state) {
        isPlaying = state;
    }

    public static void setPlayingMusic(Music music) {
        mPlayingMusic = music;
    }

    public static Music getPlayingMusic() {
        if (mPlayingMusic == null && getInstance().localMusicList.size() > 0) {
            mPlayingMusic = getInstance().localMusicList.get(0);
        }
        return mPlayingMusic;
    }

    public static void setLocalMusicList(List<Music> musicList) {
        getInstance().localMusicList = musicList;
    }

    public static List<Music> getLocalMusicList() {
        return getInstance().localMusicList;
    }

    public static void clearAll() {
        // do nothing
    }
}


