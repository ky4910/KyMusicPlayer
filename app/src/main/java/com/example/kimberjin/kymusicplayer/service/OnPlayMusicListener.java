package com.example.kimberjin.kymusicplayer.service;

/**
 * Created by ky4910 on 2019/10/10
 *
 * Provide interface for MainActivity to update bottom playBar
 *
 */

public interface OnPlayMusicListener {

    void onMusicPlay();

    void onMusicCurrentPosition(int currentPosition);

    void onMusicPause();

    void onMusicCurrentProgress(int currentProgress);

//    void onMusicComplete();
}
